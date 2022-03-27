package com.gdsc.greener.domain;

import com.gdsc.greener.repository.ContentsRepository;
import com.gdsc.greener.repository.AccountRepository;
import com.gdsc.greener.service.ContentsService;
import com.gdsc.greener.service.AccountService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

@ExtendWith(MockitoExtension.class)
public class ServiceBase {
    @Mock
    AccountRepository accountRepository;
    @Mock
    PasswordEncoder passwordEncoder;

    @Mock
    ContentsRepository contentsRepository;

    @InjectMocks
    AccountService accountService;

    @InjectMocks
    ContentsService contentsService;

    String testEmail = "greener@gmail.com";
    String testPassword = "greener";
    String testUsername = "greener";

    EmotionColor testEmotion = EmotionColor.BLUE;
    String testUrl = "https://youtu.be/pgsathBaftg";

    Account createMockUser() {
        return Account.builder()
                .name(testUsername)
                .email(testEmail)
                .password(passwordEncoder.encode(testPassword))
                .build();
    }

    Contents createMockContent() {
        return Contents.builder()
                .emotionColor(EmotionColor.MINT)
                .url("https://youtu.be/pgsathBaftg")
                .build();
    }
}
