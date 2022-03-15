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
    @Enumerated(EnumType.STRING)
    private UserStatus state;
    private String token;
    @Enumerated(EnumType.STRING)
    private Role role;

    public void updateToken(String token) {
        this.token = token;
    }

    @Builder
    public Account(String email, String name, UserStatus state, Role role) {
        this.email = email;
        this.name = name;
        this.state = state;
        this.role = role;
    }

    @Builder
    public Account(String email, String name, UserStatus state, Role role, String token) {
        this.email = email;
        this.name = name;
        this.state = state;
        this.role = role;
        this.token = token;
    }

    public Account update(String name) {
        this.name = name;
        return this;
    }
}
