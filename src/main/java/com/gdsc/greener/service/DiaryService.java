package com.gdsc.greener.service;

import com.gdsc.greener.domain.Diary;
import com.gdsc.greener.domain.DiaryType;
import com.gdsc.greener.domain.EmotionColor;
import com.gdsc.greener.dto.DiaryDto;
import com.gdsc.greener.dto.DiaryEmotionDto;
import com.gdsc.greener.repository.DiaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DiaryService {

    @Autowired
    private DiaryRepository diaryRepository;

    public DiaryDto createDiary(EmotionColor emotionColor, String diary) throws Exception{
        if(!diaryRepository.findByCreatedAt(LocalDate.now()).isPresent()) {
            return new DiaryDto(diaryRepository.save(new Diary(emotionColor, diary, DiaryType.EMOTIONAL)));
        } else {
            Diary oldDiary = diaryRepository.findByCreatedAt(LocalDate.now()).orElseThrow(Exception::new);
            oldDiary.update(emotionColor, diary, DiaryType.EMOTIONAL);
            diaryRepository.save(oldDiary);
            return new DiaryDto(oldDiary);
        }
    }

    public DiaryDto createDiary(Long id, String diary) throws Exception {
        if(!diaryRepository.findById(id).isPresent()) {
            return new DiaryDto(diaryRepository.save(new Diary(diary, DiaryType.GRATITUDE)));
        } else {
            Diary oldDiary = diaryRepository.findByCreatedAt(LocalDate.now()).orElseThrow(Exception::new);
            oldDiary.update(diary, DiaryType.GRATITUDE);
            diaryRepository.save(oldDiary);
            return new DiaryDto(oldDiary);
        }
    }

    public List<DiaryEmotionDto> getMonthDiary(LocalDate start, LocalDate end) throws Exception {
        return diaryRepository.findAllByCreatedAtBetween(start, end).stream()
                .map(DiaryEmotionDto::fromEntity).collect(Collectors.toList());
    }

    public DiaryDto getOneDiary(Long id) throws Exception {
        Diary diary = diaryRepository.findById(id).orElseThrow(Exception::new);
        return new DiaryDto(diary);
    }
}
