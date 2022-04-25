package cl.qs.securitycoreserver.service.interfaces;

import cl.qs.securitycoreserver.dto.UserAplicationAccess.UserApplicationAccessRequestDto;
import cl.qs.securitycoreserver.dto.UserAplicationAccess.UserApplicationAccessResponseDto;
import cl.qs.securitycoreserver.exception.SecurityCoreServerException;
import java.util.List;

public interface UserApplicationServiceAccessInterface {

  List<UserApplicationAccessResponseDto> findBy(
      UserApplicationAccessRequestDto applicationRequestDto)
      throws SecurityCoreServerException;

  UserApplicationAccessResponseDto save(UserApplicationAccessRequestDto applicationRequestDto)
      throws SecurityCoreServerException;

  UserApplicationAccessResponseDto update(UserApplicationAccessRequestDto applicationRequestDto)
      throws SecurityCoreServerException;

  UserApplicationAccessResponseDto deleteById(Long id) throws SecurityCoreServerException;
}
