package cl.qs.securitycoreserver.controller;

import cl.qs.securitycoreserver.dto.UserAplicationAccess.UserApplicationAccessRequestDto;
import cl.qs.securitycoreserver.dto.UserAplicationAccess.UserApplicationAccessResponseDto;
import cl.qs.securitycoreserver.exception.SecurityCoreServerException;
import cl.qs.securitycoreserver.service.interfaces.UserApplicationServiceAccessInterface;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/user_application")
@Tag(name = "Applications for Users Controller", description = "The Applications for Users API from Server Security Core ")
public class UserApplicationAccessController {

  private final UserApplicationServiceAccessInterface serviceInterface;

  @Autowired
  public UserApplicationAccessController(UserApplicationServiceAccessInterface serviceInterface) {
    this.serviceInterface = serviceInterface;
  }

  @GetMapping("/")
  public HttpEntity<List<UserApplicationAccessResponseDto>> findBy(
      @Valid UserApplicationAccessRequestDto requestDto)
      throws SecurityCoreServerException {
    return new ResponseEntity<>(serviceInterface.findBy(requestDto), HttpStatus.OK);

  }

  @PostMapping("/")
  public HttpEntity<UserApplicationAccessResponseDto> save(
      @Valid @RequestBody UserApplicationAccessRequestDto requestDto)
      throws SecurityCoreServerException {
    return new ResponseEntity<>(serviceInterface.save(requestDto), HttpStatus.CREATED);
  }

  @PutMapping("/")
  public HttpEntity<UserApplicationAccessResponseDto> update(
      @Valid @RequestBody UserApplicationAccessRequestDto requestDto)
      throws SecurityCoreServerException {
    return new ResponseEntity<>(serviceInterface.update(requestDto), HttpStatus.CREATED);
  }

  @DeleteMapping("/{id}")
  public HttpEntity<UserApplicationAccessResponseDto> deleteById(@Valid @PathVariable Long id)
      throws SecurityCoreServerException {
    return new ResponseEntity<>(serviceInterface.deleteById(id), HttpStatus.OK);
  }
}
