package cl.qs.securitycoreserver.service.impl;

import cl.qs.securitycoreserver.dto.ChangePasswordResponseDto;
import cl.qs.securitycoreserver.dto.auth.AuthRequestDto;
import cl.qs.securitycoreserver.dto.auth.AuthResponseDto;
import cl.qs.securitycoreserver.dto.auth.ChangePasswordRequestDto;
import cl.qs.securitycoreserver.entity.User;
import cl.qs.securitycoreserver.entity.UserApplicationAccess;
import cl.qs.securitycoreserver.exception.SecurityCoreServerException;
import cl.qs.securitycoreserver.repository.UserRepositoryInterface;
import cl.qs.securitycoreserver.security.JWTCreator;
import cl.qs.securitycoreserver.security.PasswordUtil;
import cl.qs.securitycoreserver.service.AuthServiceInterface;
import cl.qs.securitycoreserver.util.AuthMapper;
import cl.qs.securitycoreserver.util.Constants;
import cl.qs.securitycoreserver.util.UserMapper;
import com.auth0.jwt.algorithms.Algorithm;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceInterfaceImpl implements AuthServiceInterface {

  private final UserRepositoryInterface repositoryInterface;
  private final String secret;
  private final String expirationToken;
  private final String STATUS;

  @Autowired
  public AuthServiceInterfaceImpl(UserRepositoryInterface repositoryInterface,
      @Value("${values.security.hash}") String secret,
      @Value("${values.security.expitarionToken}") String expirationToken) {
    this.repositoryInterface = repositoryInterface;
    this.secret = secret;
    this.expirationToken = expirationToken;
    STATUS = "active";
  }

  @Override
  public AuthResponseDto login(AuthRequestDto authRequestDto) throws SecurityCoreServerException {
    Optional<User> loggedUser = itsLogged(authRequestDto);
    if (loggedUser.isPresent()) {
      User user = loggedUser.get();
      UserApplicationAccess userApplicationAccess = getUserApplicationAccess(
          authRequestDto.getApplicationId(), user.getUserApplicationAccesses());
      List<String> authoritiesList = AuthMapper.buildAuthorities(
          userApplicationAccess.getUserAuthorizations());
      Map<String, Object> defaultValues = AuthMapper.buildDefaulValues(
          userApplicationAccess.getUserAuthorizations());

      Map<String, Object> header = new HashMap<>();
      header.put("alg", "HS256");
      header.put("typ", "JWT");

      String token = JWTCreator.init()
          .withHeader(header)
          .withIssuer("jcqs")
          .withClaim("id", user.getId())
          .withClaim("dni", user.getDni())
          .withClaim("name", user.getName())
          .withClaim("email", user.getEmail())
          .withExpiresAt(getExpitationDate())
          .withArrayClaim("authorities", authoritiesList)
          .sign(Algorithm.HMAC256(secret));
      return UserMapper.buildAuthResponseDto(user, defaultValues, token);

    }
    throw new SecurityCoreServerException("0109",
        Constants.ERROR,
        Constants.USER_UNAUTHORIZED,
        HttpStatus.UNAUTHORIZED);
  }


  @Override
  public ChangePasswordResponseDto changePassword(
      ChangePasswordRequestDto changePasswordRequestDto) throws SecurityCoreServerException {
    Optional<User> loggedUser = itsLogged(changePasswordRequestDto);
    if (loggedUser.isPresent()) {
      var userToSave = UserMapper.buildChangePassword(changePasswordRequestDto.getNewPassword(),
          loggedUser.get());
      repositoryInterface.save(userToSave);

      return UserMapper.buildChangePasswordResponseDto(Constants.OK,
          Constants.UPDATED_PASSWORD);
    }
    return UserMapper.buildChangePasswordResponseDto(Constants.ERROR,
        Constants.USER_UNAUTHORIZED);
  }

  private Date getExpitationDate() {
    LocalDateTime timeExp = LocalDateTime.now();
    LocalDateTime plus = timeExp.plus(Long.parseLong(expirationToken), ChronoUnit.MONTHS);
    return Date.from(plus.atZone(ZoneId.systemDefault()).toInstant());
  }

  private UserApplicationAccess getUserApplicationAccess(Long applicationId,
      List<UserApplicationAccess> userApplicationAccesses) throws SecurityCoreServerException {
    var userAccess = userApplicationAccesses.stream()
        .filter(userApplicationAccess -> Objects.equals(userApplicationAccess.getApplicationId(),
            applicationId))
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

  private Optional<User> itsLogged(AuthRequestDto authRequestDto) {
    Optional<User> userExist = repositoryInterface.findByEmailAndStatus(
        authRequestDto.getEmail(),
        STATUS);
    if (userExist.isPresent() && PasswordUtil.checkPassword(authRequestDto.getPassword(),
        userExist.get().getPassword())) {
      return userExist;
    }
    return Optional.empty();
  }

  private Optional<User> itsLogged(ChangePasswordRequestDto authRequestDto) {
    Optional<User> userExist = repositoryInterface.findByEmailAndStatus(
        authRequestDto.getEmail(),
        STATUS);
    if (userExist.isPresent() && PasswordUtil.checkPassword(authRequestDto.getPassword(),
        userExist.get().getPassword())) {
      return userExist;
    }
    return Optional.empty();
  }
}
