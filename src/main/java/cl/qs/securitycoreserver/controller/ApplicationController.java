package cl.qs.securitycoreserver.controller;

import cl.qs.securitycoreserver.dto.application.ApplicationRequestDto;
import cl.qs.securitycoreserver.dto.application.ApplicationResponseDto;
import cl.qs.securitycoreserver.exception.SecurityCoreServerException;
import cl.qs.securitycoreserver.service.ApplicationServiceInterface;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/application")
@Tag(name = "Application Controller", description = "The level API from Server Security Core ")
public class ApplicationController {

    private final ApplicationServiceInterface serviceInterface;

    @Autowired
    public ApplicationController(ApplicationServiceInterface serviceInterface) {
        this.serviceInterface = serviceInterface;
    }

    @GetMapping("/")
    public HttpEntity<List<ApplicationResponseDto>> findAll() throws SecurityCoreServerException {
        return new ResponseEntity<>(serviceInterface.findAll(), HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public HttpEntity<ApplicationResponseDto> findById(@Valid @PathVariable Long id) throws SecurityCoreServerException {
        return new ResponseEntity<>(serviceInterface.findById(id), HttpStatus.OK);
    }

    @PostMapping("/")
    public HttpEntity<ApplicationResponseDto> save(@Valid @RequestBody ApplicationRequestDto requestDto) throws SecurityCoreServerException {
        return new ResponseEntity<>(serviceInterface.save(requestDto), HttpStatus.CREATED);
    }

    @PutMapping("/")
    public HttpEntity<ApplicationResponseDto> update(@Valid @RequestBody ApplicationRequestDto requestDto) throws SecurityCoreServerException {
        return new ResponseEntity<>(serviceInterface.update(requestDto), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<ApplicationResponseDto> deleteById(@Valid @PathVariable Long id) throws SecurityCoreServerException {
        return new ResponseEntity<>(serviceInterface.deleteById(id), HttpStatus.OK);
    }
}
