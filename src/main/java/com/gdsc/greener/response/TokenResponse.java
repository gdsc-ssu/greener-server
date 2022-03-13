package com.gdsc.greener.response;

import com.gdsc.greener.dto.TokenDto;
import lombok.Getter;

@Getter
public class TokenResponse {

    private final String accessToken;

    private final String refreshToken;

    public TokenResponse(TokenDto tokenDto) {
        this.accessToken = tokenDto.getAccessToken();
        this.refreshToken = tokenDto.getRefreshToken();
    }
}
