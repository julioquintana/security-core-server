package cl.qs.securitycoreserver.service;

import cl.qs.securitycoreserver.dto.auth.AuthRequestDto;
import cl.qs.securitycoreserver.dto.auth.AuthResponseDto;
import cl.qs.securitycoreserver.exception.SecurityCoreServerException;

public interface AuthServiceInterface {
    AuthResponseDto login(AuthRequestDto authRequestDto) throws SecurityCoreServerException;
}
