package com.gdsc.greener.controller;

import com.gdsc.greener.dto.DiaryDto;
import com.gdsc.greener.dto.DiaryEmotionDto;
import com.gdsc.greener.request.CreateDiaryEmotionRequest;
import com.gdsc.greener.request.CreateDiaryRequest;
import com.gdsc.greener.request.GetDiaryListRequest;
import com.gdsc.greener.request.GetDiaryRequest;
import com.gdsc.greener.response.DiaryResponse;
import com.gdsc.greener.response.GetDiaryListResponse;
import com.gdsc.greener.service.DiaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/diary")
@RequiredArgsConstructor
public class DiaryController {

    @Autowired
    private DiaryService diaryService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/")
    public DiaryResponse createDiary(@RequestBody CreateDiaryEmotionRequest diaryEmotionRequest) throws Exception { // user 다시 정리되면 jwt 확인 후 사용 가능
        DiaryDto diary = diaryService.createDiary(diaryEmotionRequest.getEmotionColor(), diaryEmotionRequest.getDiary());
        return new DiaryResponse(diary.getEmotionColor(), diary.getEmotionJournal(), diary.getGratitudeJournal(), diary.getCreatedAt());
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{id}")
    public DiaryResponse createDiary(@PathVariable Long id, @RequestBody CreateDiaryRequest diaryRequest) throws Exception { // user 다시 정리되면 jwt 확인 후 사용 가능
        DiaryDto diary = diaryService.createDiary(id, diaryRequest.getDiary());
        return new DiaryResponse(diary.getEmotionColor(), diary.getEmotionJournal(), diary.getGratitudeJournal(), diary.getCreatedAt());
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/")
    public GetDiaryListResponse getMonthDiary(@RequestBody GetDiaryListRequest getDiaryListRequest) throws Exception { // user 다시 정리되면 jwt 확인 후 사용 가능
        List<DiaryEmotionDto> diaryEmotionList = diaryService.getMonthDiary(getDiaryListRequest.getStart(), getDiaryListRequest.getEnd());
        return new GetDiaryListResponse(diaryEmotionList);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public DiaryResponse getOneDiary(@PathVariable Long id) throws Exception {
        DiaryDto diary = diaryService.getOneDiary(id);
        return new DiaryResponse(diary);
    }
}
