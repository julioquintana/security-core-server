package cl.qs.securitycoreserver.service;

import cl.qs.securitycoreserver.dto.LevelRequestDto;
import cl.qs.securitycoreserver.entity.Level;
import cl.qs.securitycoreserver.exception.SecurityCoreServerException;
import cl.qs.securitycoreserver.repository.LevelRepositoryInterface;
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
    public List<Level> findAll() throws SecurityCoreServerException {
        List<Level> levels = levelRepositoryInterface.findAll();
        if (!CollectionUtils.isEmpty(levels)) {
            return levels;
        }
        throw new SecurityCoreServerException("9999","ERROR" ,"No encontre Levels en la DB", HttpStatus.NOT_FOUND);
    }

    @Override
    public Level save(LevelRequestDto levelRequestDto) {
        Level level = Mapper.buildLevel(levelRequestDto);

        return levelRepositoryInterface.save(level);
    }
    @Override
    public Level update(LevelRequestDto levelRequestDto) throws SecurityCoreServerException {
        Optional<Level> levelOptional = levelRepositoryInterface.findById(levelRequestDto.getId());

        if(levelOptional.isPresent()) {
            Level level = Mapper.buildLevelUpdate(levelRequestDto, levelOptional.get());

            return levelRepositoryInterface.save(level);
        }else{
            throw new SecurityCoreServerException("9991","ERROR" ,"No encontre Level cone este ID en la DB", HttpStatus.NOT_FOUND);
        }
    }
}
