package com.gdsc.greener.domain;

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
    @Enumerated(EnumType.STRING)
    private UserStatus state;
    private String refreshToken;
    @Enumerated(EnumType.STRING)
    private Role role;

    public void updateRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    @Builder
    public Account(String email, String name, String password, UserStatus state, Role role, String refreshToken) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.state = state;
        this.role = role;
        this.refreshToken = refreshToken;
    }
}
