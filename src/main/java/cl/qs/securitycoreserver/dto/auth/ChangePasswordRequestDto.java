package cl.qs.securitycoreserver.dto.auth;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ChangePasswordRequestDto {

  @NotNull
  @Size(min = 1, max = 128)
  private String email;
  @NotNull
  @Size(min = 1, max = 512)
  private String password;
  @NotNull
  @Size(min = 1, max = 512)
  private String newPassword;
  @NotNull
  private Long applicationId;
}
