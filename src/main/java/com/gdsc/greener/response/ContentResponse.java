package com.gdsc.greener.response;

import com.gdsc.greener.domain.Contents;
import com.gdsc.greener.domain.EmotionColor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContentResponse {

    EmotionColor emotionColor;
    String url;

    public ContentResponse(Contents contents) {
        this.emotionColor = contents.getEmotionColor();
        this.url = contents.getUrl();
    }


    public ContentResponse(EmotionColor emotionColor, String url) {
        this.emotionColor = emotionColor;
        this.url = url;
    }
}
