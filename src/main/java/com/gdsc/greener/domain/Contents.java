package com.gdsc.greener.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor
public class Contents {
    @Id
    @GeneratedValue
    private Long id;
    private EmotionColor emotionColor;
    private String url;

    @Builder
    public Contents(EmotionColor emotionColor, String url){
        this.emotionColor = emotionColor;
        this.url = url;
    }
}
