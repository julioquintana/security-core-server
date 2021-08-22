package cl.qs.securitycoreserver.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class LevelRequestDto {
    @NotNull @Size(min = 1, max = 256)
    private String name;
    @NotNull @Size(min = 1, max = 1024)
    private String description;
    @NotNull @Size(min = 1, max = 128)
    private String user;
}
