package com.gdsc.greener.domain;

import com.gdsc.greener.repository.ContentsRepository;
import com.gdsc.greener.repository.UserRepository;
import com.gdsc.greener.service.ContentsService;
import com.gdsc.greener.service.UserService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

@ExtendWith(MockitoExtension.class)
public class ServiceBase {
    @Mock
    UserRepository userRepository;
    @Mock
    PasswordEncoder passwordEncoder;

    @Mock
    ContentsRepository contentsRepository;

    @InjectMocks
    UserService userService;

    @InjectMocks
    ContentsService contentsService;

    String testEmail = "greener@gmail.com";
    String testPassword = "greener";
    String testUsername = "greener";

    EmotionColor testEmotion = EmotionColor.BLUE;
    String testUrl = "https://youtu.be/pgsathBaftg";

    User createMockUser() {
        return User.builder()
                .name(testUsername)
                .email(testEmail)
                .password(passwordEncoder.encode(testPassword))
                .build();
    }

    Contents createMockContent() {
        return Contents.builder()
                .emotionColor(EmotionColor.ORANGE)
                .url("https://youtu.be/pgsathBaftg")
                .build();
    }
}
