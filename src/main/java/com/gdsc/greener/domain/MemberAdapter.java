package com.gdsc.greener.domain;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
public class MemberAdapter extends User {
    private final Account account;

    public MemberAdapter(Account account) {
        super(account.getEmail(), account.getPassword(), authorities(account.getRole()));
        this.account = account;
    }

    private static Collection<? extends GrantedAuthority> authorities(Role role) {
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        return roles.stream()
                .map(r -> new SimpleGrantedAuthority("ROLE_" + r.name()))
                .collect(Collectors.toSet());
    }

    public Account getMember() {
        return account;
    }
}
