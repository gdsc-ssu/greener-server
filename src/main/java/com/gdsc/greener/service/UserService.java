package com.gdsc.greener.service;

import com.gdsc.greener.config.auth.OAuthAttributes;
import com.gdsc.greener.config.auth.SessionUser;
import com.gdsc.greener.domain.Role;
import com.gdsc.greener.domain.User;
import com.gdsc.greener.dto.UserDto;
import com.gdsc.greener.repository.UserRepository;
import com.gdsc.greener.request.CreateUserRequest;
import com.gdsc.greener.request.UserRequest;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Collections;

/*
@RequiredArgsConstructor
@Service
public class UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    private final UserRepository userRepository;
    private final HttpSession httpSession;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        // OAuth2 서비스 id (구글, 카카오, 네이버)
        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        // OAuth2 로그인 진행 시 키가 되는 필드 값(PK)
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();

        // OAuth2UserService
        OAuthAttributes attributes = OAuthAttributes.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());
        User user = saveOrUpdate(attributes);
        httpSession.setAttribute("user", new SessionUser(user)); // SessionUser (직렬화된 dto 클래스 사용)

        return new DefaultOAuth2User(Collections.singleton(new SimpleGrantedAuthority(user.getRoleKey())),
                attributes.getAttributes(),
                attributes.getNameAttributeKey());
    }

    // 유저 생성 및 수정 서비스 로직
    private User saveOrUpdate(OAuthAttributes attributes){
        User user = userRepository.findByEmail(attributes.getEmail())
                .map(entity -> entity.update(attributes.getName(), attributes.getPicture()))
                .orElse(attributes.toEntity());
        return userRepository.save(user);
    }
}
*/


@AllArgsConstructor
@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    //Spring security 필수 구현 method
    @Override
    public User loadUserByUsername(String userID) throws UsernameNotFoundException {
        return userRepository.findById(userID).orElseThrow(() -> new UsernameNotFoundException(userID));
    }

    public void signup(CreateUserRequest createUserRequest) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        userRepository.save(new User(
                createUserRequest.getName(),
                createUserRequest.getEmail(),
                encoder.encode(createUserRequest.getPassword()),
                null,
                Role.USER
        ));
    }

    /* 로그인 */
    public UserDto signin(UserRequest userRequest) {
        String email = userRequest.getEmail();
        User user = userRepository.findByEmail(email).orElseThrow(()-> new UsernameNotFoundException(email));
        return new UserDto(user.getEmail(), user.getName());
    }
}