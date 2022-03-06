package com.gdsc.greener.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Entity
@NoArgsConstructor
public class Question {
    @Id
    @GeneratedValue
    private Long id;

    private String question;
}
