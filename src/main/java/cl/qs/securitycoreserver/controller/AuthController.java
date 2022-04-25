package cl.qs.securitycoreserver.controller;

import cl.qs.securitycoreserver.dto.ChangePasswordResponseDto;
import cl.qs.securitycoreserver.dto.auth.AuthRequestDto;
import cl.qs.securitycoreserver.dto.auth.AuthResponseDto;
import cl.qs.securitycoreserver.dto.auth.ChangePasswordRequestDto;
import cl.qs.securitycoreserver.exception.SecurityCoreServerException;
import cl.qs.securitycoreserver.service.interfaces.AuthServiceInterface;
import io.swagger.v3.oas.annotations.tags.Tag;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/auth")
@Tag(name = "Auth Controller", description = "The level API from Server Security Core ")
public class AuthController {

  private final AuthServiceInterface authServiceInterface;

  @Autowired
  public AuthController(AuthServiceInterface authServiceInterface) {
    this.authServiceInterface = authServiceInterface;
  }

  @PostMapping("/login")
  public HttpEntity<AuthResponseDto> save(@Valid @RequestBody AuthRequestDto authRequestDto)
      throws SecurityCoreServerException {
    return new ResponseEntity<>(authServiceInterface.login(authRequestDto), HttpStatus.CREATED);
  }

  @PostMapping("/change_password")
  public HttpEntity<ChangePasswordResponseDto> changePassword(
      @Valid @RequestBody ChangePasswordRequestDto authRequestDto)
      throws SecurityCoreServerException {
    return new ResponseEntity<>(
        authServiceInterface.changePassword(authRequestDto),
        HttpStatus.CREATED);
  }

}
