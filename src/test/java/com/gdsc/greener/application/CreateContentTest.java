package com.gdsc.greener.application;

import com.gdsc.greener.domain.Emotion;
import com.gdsc.greener.request.CreateContentRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CreateContentTest extends BaseControllerTest {

    @Test
    @DisplayName("콘텐츠 생성 (성공)")
    void createContentSuccess() throws Exception {
        Emotion testEmotion = Emotion.SAD;
        String testUrl = "https://youtu.be/as_vTI0_r9g";

        String request = objectMapper.writeValueAsString(
                new CreateContentRequest(testEmotion, testUrl));

        mockMvc.perform(post("/v1/admin/contents")
                .content(request)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andDo(print());
    }
}
