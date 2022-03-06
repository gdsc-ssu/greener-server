package com.gdsc.greener.request;

import com.gdsc.greener.domain.EmotionColor;
import lombok.Getter;

@Getter
public class GetContentsByEmotionRequest {
    EmotionColor emotionColor;

    public GetContentsByEmotionRequest(EmotionColor emotion) {
        this.emotionColor = emotion;
    }
}
