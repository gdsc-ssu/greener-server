package com.gdsc.greener.exception;

import lombok.Getter;

@Getter
public enum ExceptionCode {

    NOT_FOUNT_USER("1000", "Not Found User"),
    INVALID_INPUT_VALUE("2000", "Invalid Input Value"),
    INVALID_EXPECTED_TYPE("2001", "Invalid Expected Type"),
    METHOD_NOT_ALLOWED("3000", "Method Not Allowed"),
    ACCESS_DENIED("4000", "Access is Denied"),
    PERMISSION_DENIED("4000", "Permission is Denied"),
    UNKNOWN_ERROR("4000", "Unknown Error"),
    WRONG_TYPE_TOKEN("4000", "Wrong type Token"),
    EXPIRED_TOKEN("4000", "Token is Expired"),
    UNSUPPORTED_TOKEN("4000", "Unsupported Token"),
    INVALID_GRANT("4000", "Invalid Grant"),
    DATABASE_INVALID_WORKING("5000", "Database process error"),
    CUSTOM_EXCEPTION_SAMPLE("CODE", "Custom Exception Sample"),
    ;

    private final String code;
    private final String message;

    ExceptionCode(final String code, final String message) {
        this.code = code;
        this.message = message;
    }
}
