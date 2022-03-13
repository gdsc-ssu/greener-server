package com.gdsc.greener.service;

import com.gdsc.greener.domain.Account;
import com.gdsc.greener.domain.MemberAdapter;
import com.gdsc.greener.repository.AccountRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomerUserDetailService implements UserDetailsService {

    private final AccountRepository accountRepository;

    public CustomerUserDetailService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    //Spring security 필수 구현 method
    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        Account account = accountRepository.findById(Long.parseLong(id)).orElseThrow(() -> new UsernameNotFoundException(id));
        return new MemberAdapter(account);
    }
}
