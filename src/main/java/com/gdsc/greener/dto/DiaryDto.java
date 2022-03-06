package com.gdsc.greener.dto;

import com.gdsc.greener.domain.Diary;
import com.gdsc.greener.domain.Emotion;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class DiaryDto {
    Emotion emotion;
    String autoDiary;
    String diary;
    LocalDate createdAt;

    public DiaryDto(Diary diary) {
        this.emotion = diary.getEmotion();
        this.autoDiary = diary.getAutoDiary();
        this.diary = diary.getDiary();
        this.createdAt = diary.getCreatedAt();
    }
}
