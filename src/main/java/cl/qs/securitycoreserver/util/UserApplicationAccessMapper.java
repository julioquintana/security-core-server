package cl.qs.securitycoreserver.util;

import cl.qs.securitycoreserver.dto.UserAplicationAccess.UserApplicationAccessRequestDto;
import cl.qs.securitycoreserver.dto.UserAplicationAccess.UserApplicationAccessResponseDto;
import cl.qs.securitycoreserver.entity.UserApplicationAccess;
import java.util.List;
import java.util.stream.Collectors;

public class UserApplicationAccessMapper {

  private UserApplicationAccessMapper() {
    throw new IllegalStateException("Utility class");
  }

  public static UserApplicationAccess build(
      UserApplicationAccessRequestDto userApplicationAccessRequestDto) {
    return UserApplicationAccess.builder()
        .id(userApplicationAccessRequestDto.getId())
        .userId(userApplicationAccessRequestDto.getUserId())
        .applicationId(userApplicationAccessRequestDto.getApplicationId())
        .createdFor(userApplicationAccessRequestDto.getUser())
        .description(userApplicationAccessRequestDto.getDescription())
        .createdAt(null)
        .updatedAt(null)
        .application(null)
        .userAuthorizations(null)
        .user(null)
        .build();
  }

  public static UserApplicationAccess buildSearchCharacter(
      UserApplicationAccessRequestDto userApplicationAccessRequestDto) {
    return UserApplicationAccess.builder()
        .id(userApplicationAccessRequestDto.getId())
        .userId(userApplicationAccessRequestDto.getUserId())
        .applicationId(userApplicationAccessRequestDto.getApplicationId())
        .createdFor(userApplicationAccessRequestDto.getUser())
        .description(userApplicationAccessRequestDto.getDescription())
        .createdAt(null)
        .updatedAt(null)
        .application(null)
        .userAuthorizations(null)
        .user(null)
        .build();
  }

  public static UserApplicationAccessResponseDto buildUserApplicationAccessResponse(
      UserApplicationAccess userApplicationAccess) {
    return UserApplicationAccessResponseDto.builder()
        .id(userApplicationAccess.getUserId())
        .userId(userApplicationAccess.getUserId())
        .applicationId(userApplicationAccess.getApplicationId())
        .createdFor(userApplicationAccess.getCreatedFor())
        .description(userApplicationAccess.getDescription())
        .createdAt(userApplicationAccess.getCreatedAt())
        .updatedAt(userApplicationAccess.getUpdatedAt())
        .build();
  }

  public static List<UserApplicationAccessResponseDto> buildUserApplicationAccessResponseList(
      List<UserApplicationAccess> userApplicationAccesses) {
    return userApplicationAccesses.stream()
        .map(UserApplicationAccessMapper::buildUserApplicationAccessResponse)
        .collect(Collectors.toList());
  }
}
