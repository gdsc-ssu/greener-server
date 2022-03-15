package com.gdsc.greener.service;

import com.gdsc.greener.domain.*;
import com.gdsc.greener.dto.DiaryDto;
import com.gdsc.greener.dto.DiaryEmotionDto;
import com.gdsc.greener.repository.DiaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DiaryService {

    private final DiaryRepository diaryRepository;

    public DiaryDto createDiary(String emotionColor, String diary, Account account) throws Exception{
        if(!diaryRepository.findByAccountAndCreatedAt(account, LocalDate.now()).isPresent()) {
            return new DiaryDto(diaryRepository.save(new Diary(EmotionColor.valueOf(emotionColor), diary, DiaryType.EMOTIONAL, account)));
        } else {
            Diary oldDiary = diaryRepository.findByAccountAndCreatedAt(account, LocalDate.now()).orElseThrow(Exception::new);
            oldDiary.update(EmotionColor.valueOf(emotionColor), diary, DiaryType.EMOTIONAL);
            diaryRepository.save(oldDiary);
            return new DiaryDto(oldDiary);
        }
    }

    public DiaryDto createDiary(Long id, String diary, Account account) throws Exception {
        if(!diaryRepository.findById(id).isPresent()) {
            return new DiaryDto(diaryRepository.save(new Diary(diary, DiaryType.GRATITUDE)));
        } else {
            Diary oldDiary = diaryRepository.findByAccountAndCreatedAt(account, LocalDate.now()).orElseThrow(Exception::new);
            oldDiary.update(diary, DiaryType.GRATITUDE);
            diaryRepository.save(oldDiary);
            return new DiaryDto(oldDiary);
        }
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
