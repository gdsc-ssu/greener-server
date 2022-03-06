package com.gdsc.greener.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

@Getter
@Entity
@NoArgsConstructor
public class Diary {
    @Id
    @GeneratedValue
    private Long id;

    private Emotion emotion;

    @Column(name = "auto_diary", columnDefinition = "TEXT")
    private String autoDiary;

    @Column(columnDefinition = "TEXT")
    private String diary;

    @Column(name = "created_at")
    @CreatedDate
    private LocalDate createdAt;

    public Diary(Emotion emotion) {
        this.emotion = emotion;
        this.createdAt = LocalDate.now();
    }

    public Diary(String diary) {
        this.diary = diary;
        this.createdAt = LocalDate.now();
    }

    public void update(Emotion emotion) {
        this.emotion = emotion;
    }

    public void updateAutoDiary(String autoDiary) {
        this.autoDiary = autoDiary;
    }

    public void updateDiary(String diary) {
        this.diary = diary;
    }
}
