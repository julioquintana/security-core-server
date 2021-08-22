package cl.qs.securitycoreserver.exception;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ErrorInfo {

    @JsonProperty("errorInfo")
    private ApiError apiError;

}
