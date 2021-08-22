package cl.qs.securitycoreserver.util;

import cl.qs.securitycoreserver.dto.LevelRequestDto;
import cl.qs.securitycoreserver.entity.Level;

public class Mapper {
    public static Level buildLevel(LevelRequestDto levelRequestDto) {
        return Level.builder()
                .description(levelRequestDto.getDescription())
                .name(levelRequestDto.getName())
                .createdFor(levelRequestDto.getUser())
                .build();
    }
}
