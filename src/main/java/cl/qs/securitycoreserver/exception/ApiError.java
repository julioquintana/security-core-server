package cl.qs.securitycoreserver.exception;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ApiError {

    @JsonProperty("codigo")
    private String statusCode;

    @JsonProperty("severidad")
    private String severity;

    @JsonProperty("descripcion")
    private String message;


    public ApiError(SecurityCoreServerException exception) {
        this.statusCode = exception.getStatusCode();
        this.severity = exception.getSeverity();
        this.message = exception.getMessage();
    }
}
