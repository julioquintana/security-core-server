package cl.qs.securitycoreserver.service;

import cl.qs.securitycoreserver.dto.ApplicationRequestDto;
import cl.qs.securitycoreserver.dto.ApplicationResponseDto;
import cl.qs.securitycoreserver.exception.SecurityCoreServerException;

import java.util.List;

public interface ApplicationServiceInterface {
    List<ApplicationResponseDto> findAll() throws SecurityCoreServerException;

    ApplicationResponseDto save(ApplicationRequestDto applicationRequestDto) throws SecurityCoreServerException;

    ApplicationResponseDto update(ApplicationRequestDto applicationRequestDto) throws SecurityCoreServerException;

    ApplicationResponseDto findById(Long id) throws SecurityCoreServerException;

    ApplicationResponseDto deleteById(Long id) throws SecurityCoreServerException;
}
