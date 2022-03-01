package com.gdsc.greener.application;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gdsc.greener.domain.Contents;
import com.gdsc.greener.domain.Emotion;
import com.gdsc.greener.repository.ContentsRepository;
import com.gdsc.greener.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
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
    protected UserRepository userRepository;

    @Autowired
    protected ContentsRepository contentsRepository;

    @BeforeEach
    void setup() {
        // user 관련
    }

    Contents createMockContent() {
        return contentsRepository.save(
                Contents.builder()
                        .emotion(Emotion.ANGRY)
                        .url("https://youtu.be/pgsathBaftg")
                        .build()
        );
    }
}
