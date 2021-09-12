package cl.qs.securitycoreserver.util;

import cl.qs.securitycoreserver.dto.UserRequestDto;
import cl.qs.securitycoreserver.dto.UserResponseDto;
import cl.qs.securitycoreserver.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {
    private UserMapper() {
        throw new IllegalStateException("Utility class");
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
                .map(UserMapper::buildUserResponse).collect(Collectors.toList());

        Pageable pageable = userPage.getPageable();

        return new PageImpl<>(userResponseDtos, pageable, userResponseDtos.size());
    }
}
