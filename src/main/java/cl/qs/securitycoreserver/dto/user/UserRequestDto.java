package cl.qs.securitycoreserver.dto.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class UserRequestDto {
    private Long id;
    @NotNull
    private Long levelId;
    @NotNull
    @Size(min = 1, max = 16)
    private String dni;
    @NotNull
    @Size(min = 1, max = 128)
    private String prefix;
    @NotNull
    @Size(min = 1, max = 256)
    private String name;
    @NotNull
    @Size(min = 1, max = 128)
    private String email;
    @NotNull
    @Size(min = 1, max = 512)
    private String password;
    @Size(min = 1, max = 256)
    private String type;
    @NotNull
    @Size(min = 1, max = 128)
    private Long user;
}
