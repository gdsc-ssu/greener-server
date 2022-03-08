package com.gdsc.greener.service;

import com.gdsc.greener.domain.RefreshToken;
import com.gdsc.greener.domain.Role;
import com.gdsc.greener.domain.User;
import com.gdsc.greener.dto.TokenDto;
import com.gdsc.greener.jwt.JwtTokenProvider;
import com.gdsc.greener.repository.RefreshTokenRepository;
import com.gdsc.greener.repository.UserRepository;
import com.gdsc.greener.request.CreateUserRequest;
import com.gdsc.greener.request.TokenRequest;
import com.gdsc.greener.request.UserRequest;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@AllArgsConstructor
@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RefreshTokenRepository tokenRepository;
    private final JwtTokenProvider jwtTokenProvider;

    //Spring security 필수 구현 method
    @Override
    public User loadUserByUsername(String id) throws UsernameNotFoundException {
        return userRepository.findById(Long.parseLong(id)).orElseThrow(() -> new UsernameNotFoundException(id));
    }

    public void signup(CreateUserRequest createUserRequest) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

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
        String email = userRequest.getEmail();
        User user = userRepository.findByEmail(email).orElseThrow(()-> new UsernameNotFoundException(email));
        if (!passwordEncoder.matches(userRequest.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("잘못된 비밀번호입니다.");
        }

        // AccessToken, RefreshToken 발급
        TokenDto tokenDto = jwtTokenProvider.createToken(user.getId(), user.getRole());

        RefreshToken refreshToken = RefreshToken.builder()
                .key(user.getId())
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
        RefreshToken refreshToken = tokenRepository.findByKey(user.getId()).orElseThrow(RuntimeException::new);

        if(!refreshToken.getToken().equals(tokenRequest.getRefreshToken()))
            throw new RuntimeException("refresh token is not equal");

        TokenDto newCreatedToken = jwtTokenProvider.createToken(user.getId(), user.getRole());
        RefreshToken updateRefreshToken = refreshToken.updateToken(newCreatedToken.getRefreshToken());
        tokenRepository.save(updateRefreshToken);

        return newCreatedToken;
    }
}