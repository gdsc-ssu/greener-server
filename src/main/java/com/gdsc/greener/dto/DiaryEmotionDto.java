package com.gdsc.greener.dto;

import com.gdsc.greener.domain.Diary;
import com.gdsc.greener.domain.EmotionColor;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DiaryEmotionDto {
    private Long id;
    private EmotionColor emotionColor;
    private LocalDate createdAt;

    public static DiaryEmotionDto fromEntity(Diary diary) {
        return DiaryEmotionDto.builder()
                .id(diary.getId())
                .emotionColor(diary.getEmotionColor())
                .createdAt(diary.getCreatedAt())
                .build();
    }
}
