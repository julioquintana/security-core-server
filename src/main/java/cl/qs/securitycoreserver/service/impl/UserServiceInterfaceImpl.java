package cl.qs.securitycoreserver.service.impl;

import cl.qs.securitycoreserver.dto.user.UserRequestDto;
import cl.qs.securitycoreserver.dto.user.UserResponseDto;
import cl.qs.securitycoreserver.entity.User;
import cl.qs.securitycoreserver.exception.SecurityCoreServerException;
import cl.qs.securitycoreserver.repository.UserRepositoryInterface;
import cl.qs.securitycoreserver.security.PasswordUtil;
import cl.qs.securitycoreserver.service.UserServiceInterface;
import cl.qs.securitycoreserver.util.Constants;
import cl.qs.securitycoreserver.util.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Optional;

@Service
public class UserServiceInterfaceImpl implements UserServiceInterface {

    private final UserRepositoryInterface userRepository;

    @Autowired
    public UserServiceInterfaceImpl(UserRepositoryInterface userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Page<UserResponseDto> findAll(Pageable pageable) throws SecurityCoreServerException {
        Page<User> users = userRepository.findAll(pageable);
        if (!CollectionUtils.isEmpty(users.getContent())) {
            return UserMapper.buildUserResponseList(users);
        }
        throw new SecurityCoreServerException("9999", Constants.ERROR, Constants.USER_NOT_FOUND, HttpStatus.NOT_FOUND);
    }

    @Override
    public UserResponseDto save(UserRequestDto userRequestDto) throws SecurityCoreServerException {
        Optional<User> optionalUser = userRepository.findByDni(userRequestDto.getDni());
        if (optionalUser.isEmpty()) {
            userRequestDto.setPassword(PasswordUtil.getHashPassword(userRequestDto.getPassword()));
            return UserMapper.buildUserResponse(userRepository.save(UserMapper.buildUser(userRequestDto)));
        } else {
            throw new SecurityCoreServerException("9991",
                    Constants.ERROR, Constants.USER_FOUND + " [" + userRequestDto.getDni() + "]",
                    HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public UserResponseDto update(UserRequestDto userRequestDto) throws SecurityCoreServerException {
        Optional<User> optionalUser = userRepository.findById(userRequestDto.getId());

        if (optionalUser.isPresent()) {
            User level = UserMapper.buildUserUpdate(userRequestDto, optionalUser.get());

            return UserMapper.buildUserResponse(userRepository.save(level));
        } else {
            throw new SecurityCoreServerException("9991", Constants.ERROR, Constants.USER_NOT_FOUND, HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public UserResponseDto findById(Long id) throws SecurityCoreServerException {
        Optional<User> userOptional = userRepository.findById(id);

        if (userOptional.isPresent()) {
            return UserMapper.buildUserResponse(userOptional.get());
        } else {
            throw new SecurityCoreServerException("9992", Constants.ERROR, Constants.USER_NOT_FOUND, HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public UserResponseDto deleteById(Long id) throws SecurityCoreServerException {
        Optional<User> userOptional = userRepository.findById(id);

        if (userOptional.isPresent()) {
            userRepository.deleteById(id);
            return UserMapper.buildUserResponse(userOptional.get());
        } else {
            throw new SecurityCoreServerException("9993", Constants.ERROR, Constants.USER_NOT_FOUND, HttpStatus.NOT_FOUND);
        }
    }
}
