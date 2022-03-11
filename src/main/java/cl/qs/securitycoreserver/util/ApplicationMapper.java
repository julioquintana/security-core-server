package cl.qs.securitycoreserver.util;

import cl.qs.securitycoreserver.dto.application.ApplicationRequestDto;
import cl.qs.securitycoreserver.dto.application.ApplicationResponseDto;
import cl.qs.securitycoreserver.entity.Application;
import java.util.List;
import java.util.stream.Collectors;

public class ApplicationMapper {

  private ApplicationMapper() {
    throw new IllegalStateException("Utility class");
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

  public static Application buildApplicationUpdate(ApplicationRequestDto applicationRequestDto,
      Application application) {
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

  public static List<ApplicationResponseDto> buildApplicationResponseList(
      List<Application> levels) {
    return levels.stream().map(ApplicationMapper::buildApplicationResponse)
        .collect(Collectors.toList());
  }

  public static Application build(ApplicationRequestDto applicationRequestDto) {
    return Application.builder()
        .id(applicationRequestDto.getId())
        .dni(applicationRequestDto.getDni())
        .name(applicationRequestDto.getName())
        .address(applicationRequestDto.getAddress())
        .currencySymbol(applicationRequestDto.getCurrencySymbol())
        .phone(applicationRequestDto.getPhone())
        .contact(applicationRequestDto.getContact())
        .status(null)
        .build();
  }
}
