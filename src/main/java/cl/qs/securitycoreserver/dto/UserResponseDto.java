package cl.qs.securitycoreserver.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

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
    @JsonProperty("created_at")
    private Timestamp createdAt;
    @JsonProperty("updated_at")
    private Timestamp updatedAt;
    private Long user;
}
