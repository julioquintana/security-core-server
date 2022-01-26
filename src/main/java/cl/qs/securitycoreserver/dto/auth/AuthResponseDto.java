package cl.qs.securitycoreserver.dto.auth;

import lombok.*;

import java.util.Map;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponseDto {
    private Long id;
    private Long levelId;
    private String prefix;
    private String name;
    private String email;
    private String type;
    private String authorization;
    private Map<String, Object> devfaultValues;

}
