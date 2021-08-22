package cl.qs.securitycoreserver.exception;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Log4j2
@ControllerAdvice
public class ErrorHandler {
    @ExceptionHandler(SecurityCoreServerException.class)
    public ResponseEntity<ErrorInfo> securityCoreServerException(SecurityCoreServerException e) {
        return getErrorInfoResponseEntity(e);
    }

    private ResponseEntity<ErrorInfo> getErrorInfoResponseEntity(SecurityCoreServerException e) {
        ApiError a = new ApiError(e);
        ErrorInfo errorInfo = new ErrorInfo(a);
        return new ResponseEntity<>(errorInfo, e.getHttpStatusCode());
    }

}
