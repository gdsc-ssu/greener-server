package com.gdsc.greener.controller;

import com.gdsc.greener.dto.DiaryDto;
import com.gdsc.greener.request.CreateDiaryRequest;
import com.gdsc.greener.request.GetDiaryRequest;
import com.gdsc.greener.response.DiaryResponse;
import com.gdsc.greener.service.DiaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/diary")
@RequiredArgsConstructor
public class DiaryController {

    @Autowired
    private DiaryService diaryService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/")
    public DiaryResponse createDiary(@RequestBody CreateDiaryRequest diaryRequest) throws Exception { // user 다시 정리되면 jwt 확인 후 사용 가능
        DiaryDto diary = diaryService.createDiary(diaryRequest.getDiary());
        return new DiaryResponse(diary.getEmotion(), diary.getAutoDiary(), diary.getDiary(), diary.getCreatedAt());
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/")
    public DiaryResponse getDiary(@RequestBody GetDiaryRequest getDiaryRequest) throws Exception { // user 다시 정리되면 jwt 확인 후 사용 가능
        DiaryDto diary = diaryService.getDiary(getDiaryRequest.getCreatedAt());
        return new DiaryResponse(diary.getEmotion(), diary.getAutoDiary(), diary.getDiary(), diary.getCreatedAt());
    }
}
