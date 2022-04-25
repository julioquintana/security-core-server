package cl.qs.securitycoreserver.service.interfaces;

import cl.qs.securitycoreserver.dto.ChangePasswordResponseDto;
import cl.qs.securitycoreserver.dto.auth.AuthRequestDto;
import cl.qs.securitycoreserver.dto.auth.AuthResponseDto;
import cl.qs.securitycoreserver.dto.auth.ChangePasswordRequestDto;
import cl.qs.securitycoreserver.exception.SecurityCoreServerException;

public interface AuthServiceInterface {

  AuthResponseDto login(AuthRequestDto authRequestDto) throws SecurityCoreServerException;

  ChangePasswordResponseDto changePassword(ChangePasswordRequestDto authRequestDto)
      throws SecurityCoreServerException;
}
