package cl.qs.securitycoreserver.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@Builder
public class LevelResponseDto {
    private Long id;
    private String name;
    private String description;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private String user;
}
