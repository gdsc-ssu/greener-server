package com.gdsc.greener.domain;

import lombok.Getter;

@Getter
public enum EmotionColor {
    MINT("MINT"), BLUE("BLUE"), GREEN("GREEN");

    private final String name;

    EmotionColor(String name) {
        this.name = name;
    }
}
