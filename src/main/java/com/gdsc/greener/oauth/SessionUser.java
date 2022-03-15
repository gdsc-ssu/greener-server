package com.gdsc.greener.oauth;

import com.gdsc.greener.domain.Account;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class SessionUser implements Serializable {
    private String name;
    private String email;

    public SessionUser(Account account) {
        this.name = account.getName();
        this.email = account.getEmail();
    }
}