package com.gdsc.greener.response;

import com.gdsc.greener.domain.Contents;

import java.util.List;

public class ContentsResponse {
    List<Contents> contentsList;

    public ContentsResponse(List<Contents> list) {
        this.contentsList = list;
    }
}
