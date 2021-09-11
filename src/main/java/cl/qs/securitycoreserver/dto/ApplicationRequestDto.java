package cl.qs.securitycoreserver.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class ApplicationRequestDto {
    private Long id;
    @NotNull
    @Size(min = 1, max = 32)
    private String dni;
    @NotNull
    @Size(min = 1, max = 256)
    private String name;
    @Size(min = 1, max = 32)
    private String phone;
    @NotNull
    @Size(min = 1, max = 128)
    private String contact;
    @NotNull
    @Size(min = 1, max = 32)
    @JsonProperty("dni_contact")
    private String dniContact;
    @NotNull
    @Size(min = 1, max = 2056)
    private String address;
    @NotNull
    @Size(min = 1, max = 32)
    @JsonProperty("currency_symbol")
    private String currencySymbol;
    @NotNull
    private Long user;
}
