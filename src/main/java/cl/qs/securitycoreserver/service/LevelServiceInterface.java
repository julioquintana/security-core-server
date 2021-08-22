package cl.qs.securitycoreserver.service;

import cl.qs.securitycoreserver.dto.LevelRequestDto;
import cl.qs.securitycoreserver.entity.Level;
import cl.qs.securitycoreserver.exception.SecurityCoreServerException;

import java.util.List;

public interface LevelServiceInterface {
    List<Level> findAll() throws SecurityCoreServerException;
    Level save(LevelRequestDto levelRequestDto);
}
