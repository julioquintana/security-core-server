package cl.qs.securitycoreserver.util;

import cl.qs.securitycoreserver.dto.*;
import cl.qs.securitycoreserver.entity.Application;
import cl.qs.securitycoreserver.entity.Level;
import cl.qs.securitycoreserver.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Mapper {
    private Mapper() {
        throw new IllegalStateException("Utility class");
    }

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

    public static List<LevelResponseDto> buildLevelResponseList(List<Level> levels) {
        List<LevelResponseDto> levelResponseDtos = new ArrayList<>();
        levels.forEach(level -> levelResponseDtos.add(buildLevelResponse(level)));
        return levelResponseDtos;
    }


    public static User buildUser(UserRequestDto userRequestDto) {
        return User.builder()
                .id(userRequestDto.getId())
                .levelId(userRequestDto.getLevelId())
                .dni(userRequestDto.getDni())
                .prefix(userRequestDto.getPrefix())
                .name(userRequestDto.getName())
                .email(userRequestDto.getEmail())
                .password(userRequestDto.getPassword())
                .type(userRequestDto.getType())
                .status(true)
                .createdFor(userRequestDto.getUser())
                .build();
    }

    public static UserResponseDto buildUserResponse(User user) {
        return UserResponseDto.builder()
                .id(user.getId())
                .levelId(user.getLevelId())
                .dni(user.getDni())
                .prefix(user.getPrefix())
                .name(user.getName())
                .email(user.getEmail())
                .type(user.getType())
                .status(user.isStatus())
                .user(user.getCreatedFor())
                .updatedAt(user.getUpdatedAt())
                .createdAt(user.getCreatedAt())
                .build();
    }

    public static User buildUserUpdate(UserRequestDto userRequestDto, User user) {
        user.setPrefix(userRequestDto.getPrefix());
        user.setName(userRequestDto.getName());
        user.setUpdatedAt(null);

        return user;
    }

    public static Page<UserResponseDto> buildUserResponseList(Page<User> userPage) {
        List<User> userList = userPage.getContent();

        List<UserResponseDto> userResponseDtos = userList.stream()
                .map(Mapper::buildUserResponse).collect(Collectors.toList());

        Pageable pageable = userPage.getPageable();

        return new PageImpl<>(userResponseDtos, pageable, userResponseDtos.size());
    }


    public static Application buildApplication(ApplicationRequestDto applicationRequestDto) {
        return Application.builder()
                .id(applicationRequestDto.getId())
                .dni(applicationRequestDto.getDni())
                .name(applicationRequestDto.getName())
                .phone(applicationRequestDto.getPhone())
                .contact(applicationRequestDto.getContact())
                .dniContact(applicationRequestDto.getDniContact())
                .address(applicationRequestDto.getAddress())
                .currencySymbol(applicationRequestDto.getCurrencySymbol())
                .status(true)
                .createdFor(applicationRequestDto.getUser())
                .build();
    }

    public static Application buildApplicationUpdate(ApplicationRequestDto applicationRequestDto, Application application) {
        application.setDni(applicationRequestDto.getDni());
        application.setName(applicationRequestDto.getName());
        application.setPhone(applicationRequestDto.getPhone());
        application.setContact(applicationRequestDto.getContact());
        application.setDniContact(applicationRequestDto.getDniContact());
        application.setAddress(applicationRequestDto.getAddress());
        application.setCurrencySymbol(applicationRequestDto.getCurrencySymbol());
        application.setUpdatedAt(null);

        return application;
    }

    public static ApplicationResponseDto buildApplicationResponse(Application application) {
        return ApplicationResponseDto.builder()
                .id(application.getId())
                .dni(application.getDni())
                .name(application.getName())
                .phone(application.getPhone())
                .contact(application.getContact())
                .dniContact(application.getDniContact())
                .address(application.getAddress())
                .currencySymbol(application.getCurrencySymbol())
                .createdAt(application.getCreatedAt())
                .updatedAt(application.getUpdatedAt())
                .status(true)
                .user(application.getCreatedFor())
                .build();
    }

    public static List<ApplicationResponseDto> buildApplicationResponseList(List<Application> levels) {
        return levels.stream().map(Mapper::buildApplicationResponse).collect(Collectors.toList());
    }


}
