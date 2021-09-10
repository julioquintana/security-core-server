package cl.qs.securitycoreserver.exception;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLException;

@Log4j2
@ControllerAdvice
public class ErrorHandler {
    @ExceptionHandler(SecurityCoreServerException.class)
    public ResponseEntity<ErrorInfo> securityCoreServerException(SecurityCoreServerException e) {
        return getErrorInfoResponseEntity(e);
    }

    @ExceptionHandler(SQLException.class)
    public ResponseEntity<ErrorInfo> sQLExceptionhandler(SQLException e) {
        ApiError a = new ApiError(e);
        ErrorInfo errorInfo = new ErrorInfo(a);
        return new ResponseEntity<>(errorInfo, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<ErrorInfo> getErrorInfoResponseEntity(SecurityCoreServerException e) {
        ApiError a = new ApiError(e);
        ErrorInfo errorInfo = new ErrorInfo(a);
        return new ResponseEntity<>(errorInfo, e.getHttpStatusCode());
    }

}
