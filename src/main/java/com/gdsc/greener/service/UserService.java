package com.gdsc.greener.service;

import com.gdsc.greener.domain.RefreshToken;
import com.gdsc.greener.domain.Role;
import com.gdsc.greener.domain.User;
import com.gdsc.greener.dto.TokenDto;
import com.gdsc.greener.exception.EmailDuplicateException;
import com.gdsc.greener.jwt.JwtTokenProvider;
import com.gdsc.greener.repository.RefreshTokenRepository;
import com.gdsc.greener.repository.UserRepository;
import com.gdsc.greener.request.CreateUserRequest;
import com.gdsc.greener.request.TokenRequest;
import com.gdsc.greener.request.UserRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@AllArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final RefreshTokenRepository tokenRepository;
    private final JwtTokenProvider jwtTokenProvider;


    public void signup(CreateUserRequest createUserRequest) {
        if(userRepository.findByEmail(createUserRequest.getEmail()).isPresent()) throw new EmailDuplicateException(HttpStatus.CONFLICT, createUserRequest.getEmail());

        userRepository.save(new User(
                createUserRequest.getName(),
                createUserRequest.getEmail(),
                encoder.encode(createUserRequest.getPassword()),
                Role.USER
        ));
    }

    /* 로그인 */
    @Transactional
    public TokenDto login(UserRequest userRequest) {
        User user = userRepository.findByEmail(userRequest.getEmail()).orElseThrow(()-> new UsernameNotFoundException(userRequest.getEmail()));

        if (!encoder.matches(userRequest.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("잘못된 비밀번호입니다.");
        }

        // AccessToken, RefreshToken 발급
        TokenDto tokenDto = jwtTokenProvider.createToken(user.getId(), user.getRole());

        RefreshToken refreshToken = RefreshToken.builder()
                .tokenKey(user.getId())
                .token(tokenDto.getRefreshToken())
                .build();

        tokenRepository.save(refreshToken);

        return tokenDto;
    }

    @Transactional
    public TokenDto reissue(TokenRequest tokenRequest) {
        if(!jwtTokenProvider.validateToken(tokenRequest.getRefreshToken())) {
            throw new RuntimeException("RefreshTokenException");
        }

        String accessToken = tokenRequest.getAccessToken();
        Authentication authentication = jwtTokenProvider.getAuthentication(accessToken);

        User user = userRepository.findById(Long.parseLong(authentication.getName())).orElseThrow(() -> new UsernameNotFoundException(authentication.getName()));
        RefreshToken refreshToken = tokenRepository.findByTokenKey(user.getId()).orElseThrow(RuntimeException::new);

        if(!refreshToken.getToken().equals(tokenRequest.getRefreshToken()))
            throw new RuntimeException("refresh token is not equal");

        TokenDto newCreatedToken = jwtTokenProvider.createToken(user.getId(), user.getRole());
        RefreshToken updateRefreshToken = refreshToken.updateToken(newCreatedToken.getRefreshToken());
        tokenRepository.save(updateRefreshToken);

        return newCreatedToken;
    }
}