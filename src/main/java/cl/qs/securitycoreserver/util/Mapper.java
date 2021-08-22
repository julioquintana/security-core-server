package cl.qs.securitycoreserver.util;

import cl.qs.securitycoreserver.dto.LevelRequestDto;
import cl.qs.securitycoreserver.entity.Level;

public class Mapper {
    public static Level buildLevel(LevelRequestDto levelRequestDto) {
        return Level.builder()
                .id(levelRequestDto.getId())
                .description(levelRequestDto.getDescription())
                .name(levelRequestDto.getName())
                .createdFor(levelRequestDto.getUser())
                .build();
    }
public static Level buildLevelUpdate(LevelRequestDto levelRequestDto, Level level) {
        level.setName(levelRequestDto.getName());
        level.setDescription(levelRequestDto.getDescription());
        level.setUpdatedAt(null);

        return level;
    }
}
