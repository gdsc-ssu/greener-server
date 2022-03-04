package com.gdsc.greener.controller;

import com.gdsc.greener.dto.UserDto;
import com.gdsc.greener.request.CreateUserRequest;
import com.gdsc.greener.request.UserRequest;
import com.gdsc.greener.response.UserResponse;
import com.gdsc.greener.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    /* 회원가입 */
    @PostMapping(value = "/signup")
    public HttpStatus signup(CreateUserRequest createUserRequest){
        userService.signup(createUserRequest);

        return HttpStatus.CREATED;
    }

    /* 로그인 */
    @PostMapping(value = "/signin")
    public UserResponse signin(UserRequest userRequest) {
        UserDto user = userService.signin(userRequest);
        return new UserResponse(user.getName(), user.getEmail());
    }
}
