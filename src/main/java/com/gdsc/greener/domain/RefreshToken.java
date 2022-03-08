package com.gdsc.greener.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "refresh_token")
@NoArgsConstructor
public class RefreshToken {
    @Id
    @GeneratedValue
    private String id;

    private Long key;
    private String token;

    public RefreshToken updateToken(String token) {
        this.token = token;
        return this;
    }

    @Builder
    public RefreshToken(Long key, String token) {
        this.key = key;
        this.token = token;
    }
}
