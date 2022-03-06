package com.gdsc.greener.service;

import com.gdsc.greener.domain.Diary;
import com.gdsc.greener.dto.DiaryDto;
import com.gdsc.greener.repository.DiaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class DiaryService {

    @Autowired
    private DiaryRepository diaryRepository;

    public DiaryDto createDiary(String diary) throws Exception {
        if(!diaryRepository.findByCreatedAt(LocalDate.now()).isPresent()) {
            return new DiaryDto(diaryRepository.save(new Diary(diary)));
        } else {
            Diary oldDiary = diaryRepository.findByCreatedAt(LocalDate.now()).orElseThrow(Exception::new);
            oldDiary.updateDiary(diary);
            diaryRepository.save(oldDiary);
            return new DiaryDto(oldDiary);
        }
    }

    public DiaryDto getDiary(LocalDate createdAt) throws Exception {
        Diary diary = diaryRepository.findByCreatedAt(createdAt).orElseThrow(Exception::new);
        return new DiaryDto(diary);
    }
}
