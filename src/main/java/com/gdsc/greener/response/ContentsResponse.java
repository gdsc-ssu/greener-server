package com.gdsc.greener.response;

import com.gdsc.greener.domain.Contents;
import lombok.Getter;

import java.util.List;

@Getter
public class ContentsResponse {
    List<Contents> contentsList;

    public ContentsResponse(List<Contents> list) {
        this.contentsList = list;
    }
}
