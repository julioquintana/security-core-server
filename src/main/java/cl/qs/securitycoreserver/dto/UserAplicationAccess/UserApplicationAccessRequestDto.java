package cl.qs.securitycoreserver.dto.UserAplicationAccess;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserApplicationAccessRequestDto {

  private Long id;

  @NotNull
  private Long userId;

  @NotNull
  private Long applicationId;

  @NotNull
  private Long user;

  @NotNull
  private String description;
}
