package cl.qs.securitycoreserver.dto.UserAplicationAccess;

import java.io.Serializable;
import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserApplicationAccessResponseDto implements Serializable {

  private Long id;
  private Long userId;
  private Long applicationId;
  private Long createdFor;
  private String description;
  private Timestamp createdAt;
  private Timestamp updatedAt;
}
