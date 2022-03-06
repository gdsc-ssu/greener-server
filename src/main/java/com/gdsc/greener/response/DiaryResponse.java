package com.gdsc.greener.response;

import com.gdsc.greener.domain.EmotionColor;
import com.gdsc.greener.dto.DiaryDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class DiaryResponse {
    private EmotionColor emotionColor;
    private String emotionJournal;
    private String gratitudeJournal;
    private LocalDate createdAt;

    public DiaryResponse(DiaryDto diary) {
        this.emotionColor = diary.getEmotionColor();
        this.emotionJournal = diary.getEmotionJournal();
        this.gratitudeJournal = diary.getGratitudeJournal();
        this.createdAt = diary.getCreatedAt();
    }
}
