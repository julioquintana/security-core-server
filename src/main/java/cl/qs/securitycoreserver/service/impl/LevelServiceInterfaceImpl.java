package cl.qs.securitycoreserver.service.impl;

import cl.qs.securitycoreserver.dto.level.LevelRequestDto;
import cl.qs.securitycoreserver.dto.level.LevelResponseDto;
import cl.qs.securitycoreserver.entity.Level;
import cl.qs.securitycoreserver.exception.SecurityCoreServerException;
import cl.qs.securitycoreserver.repository.LevelRepositoryInterface;
import cl.qs.securitycoreserver.service.interfaces.LevelServiceInterface;
import cl.qs.securitycoreserver.util.ApplicationMapper;
import cl.qs.securitycoreserver.util.Constants;
import cl.qs.securitycoreserver.util.LevelMapper;
import cl.qs.securitycoreserver.util.Matcher;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service
public class LevelServiceInterfaceImpl implements LevelServiceInterface {

  private final LevelRepositoryInterface levelRepositoryInterface;

  @Autowired
  public LevelServiceInterfaceImpl(LevelRepositoryInterface levelRepositoryInterface) {
    this.levelRepositoryInterface = levelRepositoryInterface;
  }

  @Override
  public List<LevelResponseDto> findBy(LevelRequestDto levelRequestDto)
      throws SecurityCoreServerException {

    Level levelChatacter = ApplicationMapper.build(levelRequestDto);

    List<Level> levels = levelRepositoryInterface.findAll(
        Example.of(levelChatacter, Matcher.getLevelMatcher()));
    if (!CollectionUtils.isEmpty(levels)) {
      return LevelMapper.buildLevelResponseList(levels);
    }
    throw new SecurityCoreServerException("9999", Constants.ERROR, Constants.LEVEL_NOT_FOUND,
        HttpStatus.NOT_FOUND);
  }


  @Override
  public LevelResponseDto save(LevelRequestDto levelRequestDto) {
    return LevelMapper.buildLevelResponse(
        levelRepositoryInterface.save(LevelMapper.buildLevel(levelRequestDto)));
  }

  @Override
  public LevelResponseDto update(LevelRequestDto levelRequestDto)
      throws SecurityCoreServerException {
    Optional<Level> levelOptional = levelRepositoryInterface.findById(levelRequestDto.getId());

    if (levelOptional.isPresent()) {
      Level level = LevelMapper.buildLevelUpdate(levelRequestDto, levelOptional.get());

      return LevelMapper.buildLevelResponse(levelRepositoryInterface.save(level));
    } else {
      throw new SecurityCoreServerException("9991", Constants.ERROR, Constants.LEVEL_NOT_FOUND,
          HttpStatus.NOT_FOUND);
    }
  }

  @Override
  public LevelResponseDto deleteById(Long id) throws SecurityCoreServerException {
    Optional<Level> levelOptional = levelRepositoryInterface.findById(id);

    if (levelOptional.isPresent()) {
      levelRepositoryInterface.deleteById(id);
      return LevelMapper.buildLevelResponse(levelOptional.get());
    } else {
      throw new SecurityCoreServerException("9993", Constants.ERROR, Constants.LEVEL_NOT_FOUND,
          HttpStatus.NOT_FOUND);
    }
  }

}
