package com.gdsc.greener.domain;

import lombok.Getter;

@Getter
public enum EmotionColor {
    ORANGE("ORNAGE"), BLUE("BLUE"), GREEN("GREEN");

    private final String name;

    EmotionColor(String name) {
        this.name = name;
    }
}
