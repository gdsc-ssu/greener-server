package com.gdsc.greener.service;

import com.gdsc.greener.domain.*;
import com.gdsc.greener.dto.DiaryDto;
import com.gdsc.greener.dto.DiaryEmotionDto;
import com.gdsc.greener.repository.DiaryRepository;
import com.google.cloud.language.v1.AnalyzeSentimentResponse;
import com.google.cloud.language.v1.Document;
import com.google.cloud.language.v1.LanguageServiceClient;
import com.google.cloud.language.v1.Sentiment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DiaryService {

    private final DiaryRepository diaryRepository;

    public DiaryDto createDiary(String diary, Account account) throws Exception{
//        System.out.println(account.getId());
        EmotionColor emotionColor = analysis(diary);
        if(!diaryRepository.findByAccountAndCreatedAt(account, LocalDate.now()).isPresent()) {
            return new DiaryDto(diaryRepository.save(new Diary(emotionColor, diary, DiaryType.EMOTIONAL, account)));
        } else {
            Diary oldDiary = diaryRepository.findByCreatedAt(LocalDate.now()).orElseThrow(Exception::new);
            oldDiary.update(emotionColor, diary, DiaryType.EMOTIONAL);
            diaryRepository.save(oldDiary);
            return new DiaryDto(oldDiary);
        }
    }

    public DiaryDto createDiary(Long id, String diary, Account account) throws Exception {
        analysis(diary);
        if(!diaryRepository.findById(id).isPresent()) {
            return new DiaryDto(diaryRepository.save(new Diary(diary, DiaryType.GRATITUDE)));
        } else {
            Diary oldDiary = diaryRepository.findByAccountAndCreatedAt(account, LocalDate.now()).orElseThrow(Exception::new);
            oldDiary.update(diary, DiaryType.GRATITUDE);
            diaryRepository.save(oldDiary);
            return new DiaryDto(oldDiary);
        }
    }

    public EmotionColor analysis(String text) throws IOException {
        // Instantiate the Language client com.google.cloud.language.v1.LanguageServiceClient
        try (LanguageServiceClient language = LanguageServiceClient.create()) {
            Document doc = Document.newBuilder().setContent(text).setType(Document.Type.PLAIN_TEXT).build();
            AnalyzeSentimentResponse response = language.analyzeSentiment(doc);
            Sentiment sentiment = response.getDocumentSentiment();
            System.out.printf("Sentiment magnitude: %.3f\n", sentiment.getMagnitude());
            System.out.printf("Sentiment score: %.3f\n", sentiment.getScore());

            if (sentiment.getScore() < 0)
                return EmotionColor.BLUE;
            else
                return EmotionColor.MINT;
        }
    }

    public DiaryDto change(Long id) throws Exception {
        Diary diary = diaryRepository.findById(id).orElseThrow(Exception::new);
        diary.update(EmotionColor.GREEN);
        diaryRepository.save(diary);
        return new DiaryDto(diary);
    }

    public List<DiaryEmotionDto> getMonthDiary(Account account, LocalDate start, LocalDate end) throws Exception {
        return diaryRepository.findAllByAccountAndCreatedAtBetween(account, start, end).stream()
                .map(DiaryEmotionDto::fromEntity).collect(Collectors.toList());
    }

    public DiaryDto getOneDiary(Long id) throws Exception {
        Diary diary = diaryRepository.findById(id).orElseThrow(Exception::new);
        return new DiaryDto(diary);
    }
}
