package com.gdsc.greener.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

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
}
