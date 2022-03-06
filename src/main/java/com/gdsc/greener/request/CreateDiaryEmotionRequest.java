package com.gdsc.greener.request;

import com.gdsc.greener.domain.EmotionColor;
import lombok.Getter;

@Getter
public class CreateDiaryEmotionRequest {
    EmotionColor emotionColor;
    String diary;
}
