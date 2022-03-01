package com.gdsc.greener.application;

import com.gdsc.greener.domain.Emotion;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.assertj.core.api.Assertions.*;

public class GetContentsTest extends BaseControllerTest {

    @Test
    @DisplayName("콘텐츠 전체 보기 (성공)")
    void getContentsSuccess() throws Exception {

        createMockContent();
        
        mockMvc.perform(get("/v1/admin/contents")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("콘텐츠 특정 감정에 따 가져오기 (성공)")
    void getContentsByEmotionSuccess() throws Exception {

        createMockContent();

        mockMvc.perform(get("/v1/contents")
                .content(String.valueOf(Emotion.ANGRY))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }
}
