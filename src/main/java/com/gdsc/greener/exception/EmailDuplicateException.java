package com.gdsc.greener.exception;

public class EmailDuplicateException extends RuntimeException {
    public EmailDuplicateException (String email){
        super(email + "은 이미 존재합니다.");
    }
}
