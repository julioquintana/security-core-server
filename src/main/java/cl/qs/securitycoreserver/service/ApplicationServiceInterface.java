package cl.qs.securitycoreserver.service;

import cl.qs.securitycoreserver.dto.application.ApplicationRequestDto;
import cl.qs.securitycoreserver.dto.application.ApplicationResponseDto;
import cl.qs.securitycoreserver.exception.SecurityCoreServerException;
import java.util.List;

public interface ApplicationServiceInterface {

  List<ApplicationResponseDto> findBy(ApplicationRequestDto applicationRequestDto)
      throws SecurityCoreServerException;

  ApplicationResponseDto save(ApplicationRequestDto applicationRequestDto)
      throws SecurityCoreServerException;

  ApplicationResponseDto update(ApplicationRequestDto applicationRequestDto)
      throws SecurityCoreServerException;

  ApplicationResponseDto deleteById(Long id) throws SecurityCoreServerException;
}
