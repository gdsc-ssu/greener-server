package com.gdsc.greener.service;

import com.gdsc.greener.domain.Account;
import com.gdsc.greener.domain.Contents;
import com.gdsc.greener.domain.Diary;
import com.gdsc.greener.domain.EmotionColor;
import com.gdsc.greener.repository.ContentsRepository;
import com.gdsc.greener.repository.DiaryRepository;
import com.gdsc.greener.request.CreateContentRequest;
import com.gdsc.greener.response.ContentsResponse;
import com.gdsc.greener.response.ContentResponse;
import lombok.RequiredArgsConstructor;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@RequiredArgsConstructor
@Service
public class ContentsService {

    @Autowired
    private ContentsRepository contentsRepository;
    @Autowired
    private DiaryRepository diaryRepository;

    public ContentResponse createContent(CreateContentRequest createContentRequest){ // user 다시 정리되면 jwt 확인 후 사용 가능
        return new ContentResponse(contentsRepository.save(
                Contents.builder()
                        .emotionColor(createContentRequest.getEmotionColor())
                        .url(createContentRequest.getUrl())
                        .build()
        ));
    }

    public ContentsResponse getContents() {
        return new ContentsResponse(contentsRepository.findAll());
    }

    public ContentsResponse getContentsByEmotion(Account account) throws Exception {
        Diary diary = diaryRepository.findByAccountAndCreatedAt(account, LocalDate.now()).orElseThrow(Exception::new);
        return new ContentsResponse(contentsRepository.findAllByEmotionColor(diary.getEmotionColor()));
    }
}