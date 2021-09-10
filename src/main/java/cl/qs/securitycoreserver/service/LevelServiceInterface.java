package cl.qs.securitycoreserver.service;

import cl.qs.securitycoreserver.dto.LevelRequestDto;
import cl.qs.securitycoreserver.dto.LevelResponseDto;
import cl.qs.securitycoreserver.exception.SecurityCoreServerException;

import java.util.List;

public interface LevelServiceInterface {
    List<LevelResponseDto> findAll() throws SecurityCoreServerException;
    LevelResponseDto save(LevelRequestDto levelRequestDto);
    LevelResponseDto update(LevelRequestDto levelRequestDto) throws SecurityCoreServerException;
    LevelResponseDto findById(Long id) throws SecurityCoreServerException;

    LevelResponseDto deleteById(Long id) throws SecurityCoreServerException;
}
