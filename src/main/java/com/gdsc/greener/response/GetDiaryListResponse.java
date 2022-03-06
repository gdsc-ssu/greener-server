package com.gdsc.greener.response;

import com.gdsc.greener.dto.DiaryEmotionDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GetDiaryListResponse {
    List<DiaryEmotionDto> diaryEmotionList;
}
