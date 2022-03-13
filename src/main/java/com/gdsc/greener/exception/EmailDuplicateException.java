package com.gdsc.greener.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class EmailDuplicateException extends RuntimeException {
    private HttpStatus httpStatus;
    private String message;

    public EmailDuplicateException (HttpStatus httpStatus, String email){
        this.httpStatus = httpStatus;
        this.message = email + " exists";
    }
}
