package com.gdsc.greener.domain;

import com.gdsc.greener.request.CreateContentRequest;
import com.gdsc.greener.response.ContentResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

public class CreateContentTest extends ServiceBase{
    @Test
    @DisplayName("콘텐츠 생성")
    void createContents() {
        given(contentsRepository.save(any(Contents.class)))
                .willReturn(
                        Contents.builder()
                                .emotionColor(EmotionColor.BLUE)
                                .url("https://youtu.be/pgsathBaftg")
                                .build()
                );

        CreateContentRequest createContentRequest = new CreateContentRequest(EmotionColor.BLUE, "https://youtu.be/pgsathBaftg");

        ContentResponse res = contentsService.createContent(createContentRequest);

        assertThat(res.getEmotionColor()).isEqualTo(testEmotion);
        assertThat(res.getUrl()).isEqualTo(testUrl);
    }
}
