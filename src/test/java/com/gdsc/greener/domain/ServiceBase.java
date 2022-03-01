package com.gdsc.greener.domain;

import com.gdsc.greener.repository.ContentsRepository;
import com.gdsc.greener.service.ContentsService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ServiceBase {
    @Mock
    ContentsRepository contentsRepository;

    @InjectMocks
    ContentsService contentsService;

    Emotion testEmotion = Emotion.ANGRY;
    String testUrl = "https://youtu.be/pgsathBaftg";

    Contents createMockContent() {
        Contents content = Contents.builder()
                .emotion(Emotion.SAD)
                .url("https://youtu.be/pgsathBaftg")
                .build();

        System.out.println(content);
        return content;
    }
}
