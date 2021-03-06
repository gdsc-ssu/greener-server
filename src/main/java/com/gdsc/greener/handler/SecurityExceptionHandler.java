package com.gdsc.greener.handler;

import com.gdsc.greener.exception.EmailDuplicateException;
import com.gdsc.greener.exception.InvalidUserException;
import com.gdsc.greener.response.ErrorResponse;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;


/**
 * 회원 인증상에서 발생하는 Exception Handler
 */
@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class SecurityExceptionHandler {
    /**
     * 이메일 중복 예외 발생
     */
    @ExceptionHandler(EmailDuplicateException.class)
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ResponseBody
    public ErrorResponse handleIdExists(EmailDuplicateException exception) {
        return new ErrorResponse(HttpStatus.ACCEPTED, "0001", exception.getMessage());
    }

    /**
     * 회원 인증 예외 발생
     */
    @ExceptionHandler(InvalidUserException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ResponseBody
    public ErrorResponse handleUserNotFound(InvalidUserException exception) {
        return new ErrorResponse(HttpStatus.FORBIDDEN, "0002", exception.getMessage());
    }

    /**
     * 서명이 유효하지 않은 예외 발생
     */
    @ExceptionHandler(SignatureException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ResponseBody
    public ErrorResponse handleSignature(SignatureException exception) {
        return new ErrorResponse(HttpStatus.FORBIDDEN, "0003", "유효하지 않은 토큰입니다.");
    }

    /**
     * 데이터가 깨진 토큰 예외 발생
     */
    @ExceptionHandler(MalformedJwtException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ResponseBody
    public ErrorResponse handleMalformedJwt(MalformedJwtException exception) {
        return new ErrorResponse(HttpStatus.FORBIDDEN, "0004", "손상된 토큰입니다.");
    }

    /**
     * 토큰 만료 예외 발생
     */
    @ExceptionHandler(ExpiredJwtException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ResponseBody
    public ErrorResponse handleTokenExpired(ExpiredJwtException exception) {
        return new ErrorResponse(HttpStatus.FORBIDDEN, "0006", "만료된 토큰입니다.");
    }
}
