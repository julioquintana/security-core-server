package cl.qs.securitycoreserver.util;

import cl.qs.securitycoreserver.dto.LevelRequestDto;
import cl.qs.securitycoreserver.dto.LevelResponseDto;
import cl.qs.securitycoreserver.entity.Level;

import java.util.ArrayList;
import java.util.List;

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

    public static LevelResponseDto buildLevelResponse(Level level) {
        return LevelResponseDto
                .builder()
                .id(level.getId())
                .name(level.getName())
                .description(level.getDescription())
                .createdAt(level.getCreatedAt())
                .updatedAt(level.getUpdatedAt())
                .user(level.getCreatedFor())
                .build();
    }

    public static List<LevelResponseDto> buildRevelResponseList(List<Level> levels) {
        List<LevelResponseDto> levelResponseDtos = new ArrayList<>();
        levels.forEach(level -> levelResponseDtos.add(buildLevelResponse(level)));
        return levelResponseDtos;
    }
}
