package com.gdsc.greener.response;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
public class ErrorResponse {

    private final LocalDateTime timestamp;
    private final int status;
    private final String error;
    private final String message;

    public ErrorResponse(HttpStatus Httpstatus, String errCode, String message) {
        this.timestamp = LocalDateTime.now();
        this.status = Httpstatus.value();
        this.error = errCode;
        this.message = message;
    }
}