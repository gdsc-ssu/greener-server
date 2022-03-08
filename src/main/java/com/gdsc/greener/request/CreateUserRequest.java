package com.gdsc.greener.request;

import lombok.Getter;

@Getter
public class CreateUserRequest {
    private String email;
    private String name;
    private String password;
}
