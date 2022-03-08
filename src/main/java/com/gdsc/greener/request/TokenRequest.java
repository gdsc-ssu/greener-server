package com.gdsc.greener.request;

import lombok.Builder;
import lombok.Getter;

@Getter
public class TokenRequest {
    String accessToken;
    String refreshToken;

    @Builder
    public TokenRequest(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
