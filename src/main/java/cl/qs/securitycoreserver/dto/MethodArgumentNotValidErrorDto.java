package cl.qs.securitycoreserver.dto;

import lombok.AllArgsConstructor;

import java.util.Map;

@AllArgsConstructor
public class MethodArgumentNotValidErrorDto {
    private String code;
    private String message;
    private Map<String, String> messages;
}
