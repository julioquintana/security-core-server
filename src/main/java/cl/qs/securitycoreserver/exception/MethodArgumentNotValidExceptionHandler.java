package cl.qs.securitycoreserver.exception;

import cl.qs.securitycoreserver.dto.MethodArgumentNotValidErrorDto;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
@Log4j2
public class MethodArgumentNotValidExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request) {

        Map<String, String> messages = new LinkedHashMap<>();
        for (int index = 0; index < exception.getBindingResult().getFieldErrors().size(); index++) {
            FieldError field = exception.getBindingResult().getFieldErrors().get(index);
            messages.put(field.getField(), field.getDefaultMessage());
        }

        return new ResponseEntity<>(new MethodArgumentNotValidErrorDto(
                "8888",
                "Request Invalid",
                messages), status);
    }

}
