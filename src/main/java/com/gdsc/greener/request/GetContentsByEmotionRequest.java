package com.gdsc.greener.request;

import com.gdsc.greener.domain.EmotionColor;
import lombok.Getter;

@Getter
public class GetContentsByEmotionRequest {
    String emotionColor;

    public GetContentsByEmotionRequest(String emotionColor) {
        this.emotionColor = emotionColor;
    }
}
