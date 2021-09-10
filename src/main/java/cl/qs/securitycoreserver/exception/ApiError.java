package cl.qs.securitycoreserver.exception;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.sql.SQLException;


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

    public ApiError(SQLException e) {
        this.statusCode = HttpStatus.INTERNAL_SERVER_ERROR.toString();
        this.severity = "ERROR";
        this.message = e.getMessage();

    }
}
