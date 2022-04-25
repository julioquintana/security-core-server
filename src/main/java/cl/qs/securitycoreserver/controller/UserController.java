package cl.qs.securitycoreserver.controller;

import cl.qs.securitycoreserver.dto.user.UserRequestDto;
import cl.qs.securitycoreserver.dto.user.UserResponseDto;
import cl.qs.securitycoreserver.exception.SecurityCoreServerException;
import cl.qs.securitycoreserver.service.interfaces.UserServiceInterface;
import io.swagger.v3.oas.annotations.tags.Tag;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
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
@RequestMapping("/v1/user")
@Tag(name = "User Controller", description = "The level API from Server Security Core ")
public class UserController {

  private final UserServiceInterface userServiceInterface;

  @Autowired
  public UserController(UserServiceInterface userServiceInterface) {
    this.userServiceInterface = userServiceInterface;
  }

  @GetMapping("/")
  public HttpEntity<Page<UserResponseDto>> findAll(@SortDefault.SortDefaults({
      @SortDefault(sort = "name", direction = Sort.Direction.ASC)}) @PageableDefault(size = 10) Pageable pageable)
      throws SecurityCoreServerException {
    return new ResponseEntity<>(userServiceInterface.findAll(pageable), HttpStatus.OK);

  }

  @GetMapping("/{id}")
  public HttpEntity<UserResponseDto> findById(@Valid @PathVariable Long id)
      throws SecurityCoreServerException {
    return new ResponseEntity<>(userServiceInterface.findById(id), HttpStatus.OK);
  }

  @PostMapping("/")
  public HttpEntity<UserResponseDto> save(@Valid @RequestBody UserRequestDto userRequestDto)
      throws SecurityCoreServerException {
    return new ResponseEntity<>(userServiceInterface.save(userRequestDto), HttpStatus.CREATED);
  }

  @PutMapping("/")
  public HttpEntity<UserResponseDto> update(@Valid @RequestBody UserRequestDto userRequestDto)
      throws SecurityCoreServerException {
    return new ResponseEntity<>(userServiceInterface.update(userRequestDto), HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public HttpEntity<UserResponseDto> deleteById(@Valid @PathVariable Long id)
      throws SecurityCoreServerException {
    return new ResponseEntity<>(userServiceInterface.deleteById(id), HttpStatus.OK);
  }
}
