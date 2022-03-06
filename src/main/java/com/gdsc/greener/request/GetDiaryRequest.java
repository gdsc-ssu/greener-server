package com.gdsc.greener.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class GetDiaryRequest {
    LocalDate createdAt;
}
