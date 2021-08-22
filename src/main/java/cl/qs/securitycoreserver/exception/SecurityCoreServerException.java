package cl.qs.securitycoreserver.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

@Getter
@AllArgsConstructor
public class SecurityCoreServerException extends Exception implements Serializable {


    private static final long serialVersionUID = 1L;

    private final String statusCode;

    private final String severity;

    private final String message;

    private final HttpStatus httpStatusCode;

}
