package cl.qs.securitycoreserver.dto.auth;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class AuthRequestDto {
    @NotNull
    @Size(min = 1, max = 128)
    private String email;
    @NotNull
    @Size(min = 1, max = 512)
    private String password;
    @NotNull
    private Long applicationId;
}
