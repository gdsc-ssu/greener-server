package com.gdsc.greener.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    @GetMapping(value = "/api/login")
    public String loginPage() {

        return "index.html";
    }
}
