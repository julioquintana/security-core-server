package cl.qs.securitycoreserver.controller;

import cl.qs.securitycoreserver.dto.LevelRequestDto;
import cl.qs.securitycoreserver.dto.LevelResponseDto;
import cl.qs.securitycoreserver.exception.SecurityCoreServerException;
import cl.qs.securitycoreserver.service.LevelServiceInterface;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/level")
@Tag(name = "Level Controller", description = "The level API from Server Security Core ")
public class LevelController {

    private final LevelServiceInterface levelServiceInterface;

    @Autowired
    public LevelController(LevelServiceInterface levelServiceInterface) {
        this.levelServiceInterface = levelServiceInterface;
    }

    @GetMapping("/")
    public HttpEntity<List<LevelResponseDto>> findAll() throws  SecurityCoreServerException {
        return new ResponseEntity<>(levelServiceInterface.findAll(), HttpStatus.OK);

    }
    @GetMapping("/{id}")
    public HttpEntity<LevelResponseDto> findById(@Valid @PathVariable Long id) throws  SecurityCoreServerException {
        return new ResponseEntity<>(levelServiceInterface.findById(id), HttpStatus.OK);
    }

    @PostMapping("/")
    public HttpEntity<LevelResponseDto> save(@Valid @RequestBody LevelRequestDto levelRequestDto) {
        return new ResponseEntity<>(levelServiceInterface.save(levelRequestDto), HttpStatus.CREATED);
    }
    @PutMapping("/")
    public HttpEntity<LevelResponseDto> update(@Valid @RequestBody LevelRequestDto levelRequestDto) throws SecurityCoreServerException {
        return new ResponseEntity<>(levelServiceInterface.update(levelRequestDto), HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public HttpEntity<LevelResponseDto> deleteById(@Valid @PathVariable Long id) throws  SecurityCoreServerException {
        return new ResponseEntity<>(levelServiceInterface.deleteById(id), HttpStatus.OK);
    }
}
