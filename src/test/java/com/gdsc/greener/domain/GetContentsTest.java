package com.gdsc.greener.domain;

import com.gdsc.greener.request.GetContentsByEmotionRequest;
import com.gdsc.greener.response.ContentsResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

public class GetContentsTest extends ServiceBase{
    @Test
    @DisplayName("콘텐츠 전부 가져오기")
    void getAllContents() {

        Contents content = createMockContent();

        List<Contents> list = new ArrayList<>();
        list.add(Contents.builder()
                .emotion(content.getEmotion())
                .url(content.getUrl())
                .build());

        given(contentsRepository.findAll())
                .willReturn(
                        list
                );

        ContentsResponse res = contentsService.getContents();

        assertThat(res.getContentsList().size()).isEqualTo(1);
        assertThat(res.getContentsList().get(0).getEmotion()).isEqualTo(content.getEmotion());
        assertThat(res.getContentsList().get(0).getUrl()).isEqualTo(content.getUrl());
    }

    @Test
    @DisplayName("감정 기반 콘텐츠 가져오기")
    void getAllContentsByEmotion() {

        Contents content = createMockContent();

        List<Contents> list = new ArrayList<>();
        list.add(Contents.builder()
                .emotion(content.getEmotion())
                .url(content.getUrl())
                .build());

        given(contentsRepository.findAllByEmotion(content.getEmotion()))
                .willReturn(
                        list
                );

        ContentsResponse res = contentsService.getContentsByEmotion(new GetContentsByEmotionRequest(content.getEmotion()));

        assertThat(res.getContentsList().size()).isEqualTo(1);
        assertThat(res.getContentsList().get(0).getEmotion()).isEqualTo(content.getEmotion());
        assertThat(res.getContentsList().get(0).getUrl()).isEqualTo(content.getUrl());
    }
}
