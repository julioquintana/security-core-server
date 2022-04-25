package cl.qs.securitycoreserver.service.interfaces;

import cl.qs.securitycoreserver.dto.level.LevelRequestDto;
import cl.qs.securitycoreserver.dto.level.LevelResponseDto;
import cl.qs.securitycoreserver.exception.SecurityCoreServerException;
import java.util.List;
import javax.validation.Valid;

public interface LevelServiceInterface {

  List<LevelResponseDto> findBy(
      @Valid LevelRequestDto levelRequestDto) throws SecurityCoreServerException;

  LevelResponseDto save(LevelRequestDto levelRequestDto);

  LevelResponseDto update(LevelRequestDto levelRequestDto) throws SecurityCoreServerException;

  LevelResponseDto deleteById(Long id) throws SecurityCoreServerException;
}
