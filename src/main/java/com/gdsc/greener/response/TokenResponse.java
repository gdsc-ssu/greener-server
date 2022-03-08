package com.gdsc.greener.response;

import com.gdsc.greener.dto.TokenDto;
import lombok.Getter;

@Getter
public class TokenResponse {

    private final String grantType;

    private final String accessToken;

    private final String refreshToken;

    private final Long accessTokenExpiresIn;

    public TokenResponse(TokenDto tokenDto) {
        this.grantType = tokenDto.getGrantType();
        this.accessToken = tokenDto.getAccessToken();
        this.refreshToken = tokenDto.getRefreshToken();
        this.accessTokenExpiresIn = tokenDto.getAccessTokenExpiresIn();
    }
}
