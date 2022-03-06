package com.gdsc.greener.request;

import com.gdsc.greener.domain.EmotionColor;
import lombok.Getter;

@Getter
public class CreateContentRequest {
    EmotionColor emotionColor;
    String url;

    public CreateContentRequest(EmotionColor emotionColor, String url) {
        this.emotionColor = emotionColor;
        this.url = url;
    }
}
