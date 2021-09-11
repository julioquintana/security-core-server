package cl.qs.securitycoreserver.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserResponseDto {
    private Long id;
    private Long levelId;
    private String dni;
    private String prefix;
    private String name;
    private String email;
    private String type;
    private boolean status;
    private String user;
}
