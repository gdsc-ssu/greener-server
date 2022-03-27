package com.gdsc.greener.service;

import com.gdsc.greener.domain.Role;
import com.gdsc.greener.domain.Account;
import com.gdsc.greener.domain.UserStatus;
import com.gdsc.greener.dto.TokenDto;
import com.gdsc.greener.exception.EmailDuplicateException;
import com.gdsc.greener.exception.InvalidUserException;
import com.gdsc.greener.jwt.JwtTokenProvider;
import com.gdsc.greener.repository.AccountRepository;
import com.gdsc.greener.request.CreateUserRequest;
import com.gdsc.greener.request.TokenRequest;
import com.gdsc.greener.request.UserRequest;
import com.gdsc.greener.utils.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@AllArgsConstructor
@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final PasswordEncoder encoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final JwtUtil jwtUtil;


    public void signup(CreateUserRequest createUserRequest) {
        if (accountRepository.findByEmail(createUserRequest.getEmail()).isPresent())
            throw new EmailDuplicateException(createUserRequest.getEmail());

        accountRepository.save(new Account(
                createUserRequest.getEmail(),
                createUserRequest.getName(),
                encoder.encode(createUserRequest.getPassword()),
//                UserStatus.NORMAL,
//                Role.ROLE_USER,
                "ROLE_USER",
                jwtTokenProvider.createRefreshToken(createUserRequest.getEmail(), Role.ROLE_USER)
        ));
    }

    /* 로그인 */
    @Transactional
    public TokenDto login(UserRequest userRequest) {
        Account account = accountRepository.findByEmail(userRequest.getEmail()).orElseThrow(() -> new UsernameNotFoundException(userRequest.getEmail()));

        if (!encoder.matches(userRequest.getPassword(), account.getPassword())) {
            throw new IllegalArgumentException("잘못된 비밀번호입니다.");
        }

        return new TokenDto(
                jwtUtil.createToken(account.getId(), account.getName(), "USER"),
                account.getRefreshToken()
        );
    }
}