package com.gdsc.greener.exception;

public class InvalidUserException extends RuntimeException {
    public InvalidUserException(String id) {
        super(id + "존재하지 않거나 제제당한 계정입니다.");
    }
}