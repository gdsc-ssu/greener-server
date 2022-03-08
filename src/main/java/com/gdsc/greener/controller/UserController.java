package com.gdsc.greener.controller;

import com.gdsc.greener.dto.TokenDto;
import com.gdsc.greener.request.CreateUserRequest;
import com.gdsc.greener.request.TokenRequest;
import com.gdsc.greener.request.UserRequest;
import com.gdsc.greener.response.TokenResponse;
import com.gdsc.greener.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    /* 회원가입 */
    @PostMapping(value = "/signup")
    public HttpStatus signup(@RequestBody CreateUserRequest createUserRequest){
        userService.signup(createUserRequest);

        return HttpStatus.CREATED;
    }

    /* 로그인 */
    @PostMapping(value = "/login")
    public TokenResponse login(@RequestBody UserRequest userRequest) {
        return new TokenResponse(userService.login(userRequest));
    }

    /* 토큰 재발급 */
    @PostMapping("/reissue")
    public TokenResponse reissue(@RequestBody TokenRequest tokenRequest) {
        return new TokenResponse(userService.reissue(tokenRequest));
    }
}
