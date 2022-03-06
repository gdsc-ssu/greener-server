package com.gdsc.greener.response;

import com.gdsc.greener.domain.Emotion;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class DiaryResponse {
    private Emotion emotion;

    private String autoDiary;

    private String diary;

    private LocalDate createdAt;
}
