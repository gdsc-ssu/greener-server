package com.gdsc.greener.dto;

import com.gdsc.greener.domain.Diary;
import com.gdsc.greener.domain.EmotionColor;
import lombok.Getter;
import java.time.LocalDate;

@Getter
public class DiaryDto {
    private EmotionColor emotionColor;
    private String emotionJournal;
    private String gratitudeJournal;
    private LocalDate createdAt;

    public DiaryDto(Diary diary) {
        this.emotionColor = diary.getEmotionColor();
        this.emotionJournal = diary.getEmotionJournal();
        this.gratitudeJournal = diary.getGratitudeJournal();
        this.createdAt = diary.getCreatedAt();
    }
}
