package com.example.swagger.config;

import lombok.Data;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * @author kunanan.t
 */

@Getter
public class CustomizeException extends CommonException {
    private final HttpStatus status = HttpStatus.BAD_REQUEST;
    private final String code = String.valueOf(HttpStatus.BAD_REQUEST.value());

    public CustomizeException(String message) {
        super(message);
    }

}
