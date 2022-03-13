package com.gdsc.greener.application;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gdsc.greener.domain.Contents;
import com.gdsc.greener.domain.EmotionColor;
import com.gdsc.greener.domain.Account;
import com.gdsc.greener.repository.ContentsRepository;
import com.gdsc.greener.repository.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;

@Disabled
@SpringBootTest
@AutoConfigureMockMvc
@Rollback
@Transactional
@RunWith(SpringRunner.class)
public class BaseControllerTest {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @Autowired
    protected AccountRepository accountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    protected ContentsRepository contentsRepository;

    String testEmail = "greener@gmail.com";
    String testPassword = "greener";
    String testUsername = "greener";

    @BeforeEach
    void setup() {
        accountRepository.save(Account.builder()
                .name(testUsername)
                .email(testEmail)
                .password(passwordEncoder.encode(testPassword))
                .build());
    }

    void createMockContent() {
        contentsRepository.save(
                Contents.builder()
                        .emotionColor(EmotionColor.BLUE)
                        .url("https://youtu.be/pgsathBaftg")
                        .build()
        );
    }
}
