package cl.qs.securitycoreserver.dto.application;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@Builder
public class ApplicationResponseDto {
    private Long id;
    private String dni;
    private String name;
    private String phone;
    private String contact;
    private String dniContact;
    private String address;
    @JsonProperty("currency_symbol")
    private String currencySymbol;
    private boolean status;
    @JsonProperty("created_at")
    private Timestamp createdAt;
    @JsonProperty("updated_at")
    private Timestamp updatedAt;
    private Long user;
}
