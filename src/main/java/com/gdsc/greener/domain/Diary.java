package com.gdsc.greener.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Entity
@NoArgsConstructor
public class Diary {
    @Id
    @GeneratedValue
    private Long id;

    private EmotionColor emotionColor;

    @Column(name = "emotion_journal", columnDefinition = "TEXT")
    private String emotionJournal;

    @Column(name = "gratitude_journal", columnDefinition = "TEXT")
    private String gratitudeJournal;

    @Column(name = "created_at")
    @CreatedDate
    private LocalDate createdAt;

    @ManyToOne
    private Account account;

    // user 수정 후 삭제
    public Diary(String journal, DiaryType diaryType) {
        if(diaryType == DiaryType.EMOTIONAL)
            this.emotionJournal = journal;
        else if(diaryType == DiaryType.GRATITUDE)
            this.gratitudeJournal = journal;
        this.createdAt = LocalDate.now();
    }

    public Diary(EmotionColor emotionColor, String journal, DiaryType diaryType) {
        this.emotionColor = emotionColor;
        if(diaryType == DiaryType.EMOTIONAL)
            this.emotionJournal = journal;
        else if(diaryType == DiaryType.GRATITUDE)
            this.gratitudeJournal = journal;
        this.createdAt = LocalDate.now();
    }

    public Diary(String journal, DiaryType diaryType, Account account) {
        this.account = account;
        if(diaryType == DiaryType.EMOTIONAL)
            this.emotionJournal = journal;
        else if(diaryType == DiaryType.GRATITUDE)
            this.gratitudeJournal = journal;
        this.createdAt = LocalDate.now();
    }

    public Diary(EmotionColor emotionColor, String journal, DiaryType diaryType, Account account) {
        this.emotionColor = emotionColor;
        this.account = account;
        if(diaryType == DiaryType.EMOTIONAL)
            this.emotionJournal = journal;
        else if(diaryType == DiaryType.GRATITUDE)
            this.gratitudeJournal = journal;
        this.createdAt = LocalDate.now();
    }

    public void update(EmotionColor emotionColor) {
        this.emotionColor = emotionColor;
    }
    public void update(String journal, DiaryType diaryType) {
        if(diaryType == DiaryType.EMOTIONAL)
            this.emotionJournal = journal;
        else if(diaryType == DiaryType.GRATITUDE)
            this.gratitudeJournal = journal;
    }

    public void update(EmotionColor emotionColor, String journal, DiaryType diaryType) {
        this.emotionColor = emotionColor;
        if(diaryType == DiaryType.EMOTIONAL)
            this.emotionJournal = journal;
        else if(diaryType == DiaryType.GRATITUDE)
            this.gratitudeJournal = journal;
    }
}
