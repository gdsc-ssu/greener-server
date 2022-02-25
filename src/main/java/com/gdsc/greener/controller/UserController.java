package com.gdsc.greener.controller;

import com.gdsc.greener.config.auth.SessionUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class UserController {
    private final HttpSession httpSession;

    @GetMapping("/")
    public HttpStatus main(){

        SessionUser user = (SessionUser) httpSession.getAttribute("user");

        return HttpStatus.OK;
    }
}
