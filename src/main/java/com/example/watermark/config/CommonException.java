package com.example.watermark.config;

import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 * @author kunanan.t
 */

@Data
public abstract class CommonException extends RuntimeException {

    protected HttpStatus status;
    protected String code;

    public CommonException(String message) {
        super(message);
    }
}
