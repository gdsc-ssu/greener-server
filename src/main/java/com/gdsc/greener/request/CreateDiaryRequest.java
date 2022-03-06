package com.gdsc.greener.request;

import lombok.Getter;

@Getter
public class CreateDiaryRequest {
    String diary;

    public CreateDiaryRequest(String diary) {
        this.diary = diary;
    }
}
