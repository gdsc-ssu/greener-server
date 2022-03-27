package com.gdsc.greener.domain;

import io.jsonwebtoken.Claims;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Account {
    @Id
    @GeneratedValue
    private Long id;
    @Column(unique = true)
    private String email;
    private String name;
    private String password;
    private String refreshToken;
    private String role;

    public Account(Claims claims) {
        this.id = Long.valueOf(claims.get("userId").toString());
        this.name = claims.get("name").toString();
        this.role = claims.get("role").toString();
    }

    @Builder
    public Account(String email, String name, String password, String role, String refreshToken) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.refreshToken = refreshToken;
    }
}
