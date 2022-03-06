package com.gdsc.greener.request;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class GetDiaryListRequest {
    LocalDate start;
    LocalDate end;
}
