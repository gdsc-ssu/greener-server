package com.gdsc.greener.request;

import com.gdsc.greener.domain.Emotion;
import lombok.Getter;

@Getter
public class GetContentsByEmotionRequest {
    Emotion emotion;
}
