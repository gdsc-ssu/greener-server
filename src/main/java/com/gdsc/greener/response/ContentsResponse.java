package com.gdsc.greener.response;

import com.gdsc.greener.domain.Contents;
import lombok.Getter;

import java.util.List;

@Getter
public class ContentsResponse {
    List<Contents> contents;

    public ContentsResponse(List<Contents> list) {
        this.contents = list;
    }
}
