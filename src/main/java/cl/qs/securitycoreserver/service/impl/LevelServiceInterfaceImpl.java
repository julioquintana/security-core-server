package cl.qs.securitycoreserver.service.impl;

import cl.qs.securitycoreserver.dto.LevelRequestDto;
import cl.qs.securitycoreserver.dto.LevelResponseDto;
import cl.qs.securitycoreserver.entity.Level;
import cl.qs.securitycoreserver.exception.SecurityCoreServerException;
import cl.qs.securitycoreserver.repository.LevelRepositoryInterface;
import cl.qs.securitycoreserver.service.LevelServiceInterface;
import cl.qs.securitycoreserver.util.Constants;
import cl.qs.securitycoreserver.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;

@Service
public class LevelServiceInterfaceImpl implements LevelServiceInterface {

    private final LevelRepositoryInterface levelRepositoryInterface;

    @Autowired
    public LevelServiceInterfaceImpl(LevelRepositoryInterface levelRepositoryInterface) {
        this.levelRepositoryInterface = levelRepositoryInterface;
    }

    @Override
    public List<LevelResponseDto> findAll() throws SecurityCoreServerException {
        List<Level> levels = levelRepositoryInterface.findAll();
        if (!CollectionUtils.isEmpty(levels)) {
            return Mapper.buildRevelResponseList(levels);
        }
        throw new SecurityCoreServerException("9999", Constants.ERROR, Constants.LEVEL_NOT_FOUND, HttpStatus.NOT_FOUND);
    }

    @Override
    public LevelResponseDto save(LevelRequestDto levelRequestDto) {
        return Mapper.buildLevelResponse(levelRepositoryInterface.save(Mapper.buildLevel(levelRequestDto)));
    }

    @Override
    public LevelResponseDto update(LevelRequestDto levelRequestDto) throws SecurityCoreServerException {
        Optional<Level> levelOptional = levelRepositoryInterface.findById(levelRequestDto.getId());

        if (levelOptional.isPresent()) {
            Level level = Mapper.buildLevelUpdate(levelRequestDto, levelOptional.get());

            return Mapper.buildLevelResponse(levelRepositoryInterface.save(level));
        } else {
            throw new SecurityCoreServerException("9991", Constants.ERROR, Constants.LEVEL_NOT_FOUND, HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public LevelResponseDto findById(Long id) throws SecurityCoreServerException {
        Optional<Level> levelOptional = levelRepositoryInterface.findById(id);

        if (levelOptional.isPresent()) {
            return Mapper.buildLevelResponse(levelOptional.get());
        } else {
            throw new SecurityCoreServerException("9992", Constants.ERROR, Constants.LEVEL_NOT_FOUND, HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public LevelResponseDto deleteById(Long id) throws SecurityCoreServerException {
        Optional<Level> levelOptional = levelRepositoryInterface.findById(id);

        if (levelOptional.isPresent()) {
            levelRepositoryInterface.deleteById(id);
            return Mapper.buildLevelResponse(levelOptional.get());
        } else {
            throw new SecurityCoreServerException("9993", Constants.ERROR, Constants.LEVEL_NOT_FOUND, HttpStatus.NOT_FOUND);
        }
    }
}
