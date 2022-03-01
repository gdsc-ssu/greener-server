package com.gdsc.greener.response;

import com.gdsc.greener.domain.Contents;
import com.gdsc.greener.domain.Emotion;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContentResponse {
    Emotion emotion;
    String url;

    public ContentResponse(Contents contents) {
        this.emotion = contents.getEmotion();
        this.url = contents.getUrl();
    }
}
