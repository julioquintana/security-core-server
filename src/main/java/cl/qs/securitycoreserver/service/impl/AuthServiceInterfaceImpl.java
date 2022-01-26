package cl.qs.securitycoreserver.service.impl;

import cl.qs.securitycoreserver.dto.auth.AuthRequestDto;
import cl.qs.securitycoreserver.dto.auth.AuthResponseDto;
import cl.qs.securitycoreserver.entity.User;
import cl.qs.securitycoreserver.entity.UserApplicationAccess;
import cl.qs.securitycoreserver.exception.SecurityCoreServerException;
import cl.qs.securitycoreserver.repository.UserRepositoryInterface;
import cl.qs.securitycoreserver.security.JWTCreator;
import cl.qs.securitycoreserver.security.PasswordUtil;
import cl.qs.securitycoreserver.service.AuthServiceInterface;
import cl.qs.securitycoreserver.util.AuthMapper;
import cl.qs.securitycoreserver.util.Constants;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AuthServiceInterfaceImpl implements AuthServiceInterface {

    private final UserRepositoryInterface repositoryInterface;
    private final String secret;

    @Autowired
    public AuthServiceInterfaceImpl(UserRepositoryInterface repositoryInterface, @Value("${values.security.hash}") String secret) {
        this.repositoryInterface = repositoryInterface;
        this.secret = secret;
    }

    @Override
    public AuthResponseDto login(AuthRequestDto authRequestDto) throws SecurityCoreServerException {
        final String STATUS = "active";
        Optional<User> userExist = repositoryInterface.findByEmailAndStatus(authRequestDto.getEmail(), STATUS);
        if (userExist.isPresent() && PasswordUtil.checkPassword(authRequestDto.getPassword(), userExist.get().getPassword())) {
            User user = userExist.get();
            UserApplicationAccess userApplicationAccess = getUserApplicationAccess(authRequestDto.getApplicationId(), user.getUserApplicationAccesses());
            List<String> authoritiesList = AuthMapper.buildAuthorities(userApplicationAccess.getUserAuthorizations());
            Map<String, Object> defaultValues = AuthMapper.buildDefaulValues(userApplicationAccess.getUserAuthorizations());

            Map<String, Object> header = new HashMap<>();
            header.put("alg", "HS256");
            header.put("typ", "JWT");

            Calendar timeExp = Calendar.getInstance();
            timeExp.add(Calendar.MONTH, 3);

            String token = JWTCreator.init()
                    .withHeader(header)
                    .withIssuer("jcqs")
                    .withClaim("id", user.getId())
                    .withClaim("dni", user.getDni())
                    .withClaim("name", user.getName())
                    .withClaim("email", user.getEmail())
                    .withExpiresAt(timeExp.getTime())
                    .withArrayClaim("authorities", authoritiesList)
                    .sign(Algorithm.HMAC256(secret));
            return AuthResponseDto.builder()
                    .id(user.getId())
                    .type(user.getType())
                    .name(user.getName())
                    .email(user.getEmail())
                    .levelId(user.getLevelId())
                    .prefix(user.getPrefix())
                    .authorization(token)
                    .devfaultValues(defaultValues)
                    .build();

        }
        throw new SecurityCoreServerException("0109",
                Constants.ERROR,
                Constants.USER_UNAUTHORIZED,
                HttpStatus.UNAUTHORIZED);
    }

    private UserApplicationAccess getUserApplicationAccess(Long applicationId, List<UserApplicationAccess> userApplicationAccesses) throws SecurityCoreServerException {
        var userAccess = userApplicationAccesses.stream()
                .filter(userApplicationAccess -> userApplicationAccess.getApplicationId() == applicationId)
                .findFirst();

        if (userAccess.isPresent()) {
            return userAccess.get();
        } else {
            throw new SecurityCoreServerException("0109",
                    Constants.ERROR,
                    Constants.USER_APP_UNAUTHORIZED,
                    HttpStatus.UNAUTHORIZED);
        }
    }
}
