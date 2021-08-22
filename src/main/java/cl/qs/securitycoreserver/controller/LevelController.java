package cl.qs.securitycoreserver.controller;

import cl.qs.securitycoreserver.dto.LevelRequestDto;
import cl.qs.securitycoreserver.entity.Level;
import cl.qs.securitycoreserver.exception.SecurityCoreServerException;
import cl.qs.securitycoreserver.service.LevelServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class LevelController {

    private final LevelServiceInterface levelServiceInterface;

    @Autowired
    public LevelController(LevelServiceInterface levelServiceInterface) {
        this.levelServiceInterface = levelServiceInterface;
    }

    @GetMapping
    public HttpEntity<List<Level>> findAll() throws  SecurityCoreServerException {
        return new ResponseEntity<>(levelServiceInterface.findAll(), HttpStatus.OK);

    }
    @PostMapping
    public HttpEntity<Level> save(@Valid @RequestBody LevelRequestDto levelRequestDto) {
        return new ResponseEntity<>(levelServiceInterface.save(levelRequestDto), HttpStatus.CREATED);
    }
}
