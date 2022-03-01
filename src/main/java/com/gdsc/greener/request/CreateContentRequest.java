package com.gdsc.greener.request;

import com.gdsc.greener.domain.Emotion;
import lombok.Getter;

@Getter
public class CreateContentRequest {
    Emotion emotion;
    String url;

    public CreateContentRequest(Emotion emotion, String url) {
        this.emotion = emotion;
        this.url = url;
    }
}
