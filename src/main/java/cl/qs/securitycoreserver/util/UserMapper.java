package cl.qs.securitycoreserver.util;

import cl.qs.securitycoreserver.dto.ChangePasswordResponseDto;
import cl.qs.securitycoreserver.dto.auth.AuthResponseDto;
import cl.qs.securitycoreserver.dto.user.UserRequestDto;
import cl.qs.securitycoreserver.dto.user.UserResponseDto;
import cl.qs.securitycoreserver.entity.User;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

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
        .status("active")
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
        .status(user.getStatus())
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

  public static User buildChangePassword(String passwords, User user) {
    user.setPassword(passwords);
    return user;
  }

  public static Page<UserResponseDto> buildUserResponseList(Page<User> userPage) {
    List<User> userList = userPage.getContent();

    List<UserResponseDto> userResponseDtos = userList.stream()
        .map(UserMapper::buildUserResponse).collect(Collectors.toList());

    Pageable pageable = userPage.getPageable();

    return new PageImpl<>(userResponseDtos, pageable, userResponseDtos.size());
  }

  public static AuthResponseDto buildAuthResponseDto(User user, Map<String, Object> defaultValues,
      String token) {
    return AuthResponseDto.builder()
        .id(user.getId())
        .type(user.getType())
        .name(user.getName())
        .email(user.getEmail())
        .levelId(user.getLevelId())
        .prefix(user.getPrefix())
        .authorization(token)
        .devfaultValues(defaultValues)
        .build();
  }

  public static ChangePasswordResponseDto buildChangePasswordResponseDto(String status,
      String description) {
    return ChangePasswordResponseDto.builder()
        .status(status)
        .description(description)
        .build();
  }
}
