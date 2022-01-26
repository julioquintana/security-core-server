package cl.qs.securitycoreserver.service;

import cl.qs.securitycoreserver.dto.user.UserRequestDto;
import cl.qs.securitycoreserver.dto.user.UserResponseDto;
import cl.qs.securitycoreserver.exception.SecurityCoreServerException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserServiceInterface {
    Page<UserResponseDto> findAll(Pageable pageable) throws SecurityCoreServerException;

    UserResponseDto save(UserRequestDto userRequestDto) throws SecurityCoreServerException;

    UserResponseDto update(UserRequestDto userRequestDto) throws SecurityCoreServerException;

    UserResponseDto findById(Long id) throws SecurityCoreServerException;

    UserResponseDto deleteById(Long id) throws SecurityCoreServerException;
}
