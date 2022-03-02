package com.gdsc.greener.controller;

import com.gdsc.greener.request.CreateContentRequest;
import com.gdsc.greener.request.GetContentsByEmotionRequest;
import com.gdsc.greener.response.ContentResponse;
import com.gdsc.greener.response.ContentsResponse;
import com.gdsc.greener.service.ContentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class ContentsController {

    @Autowired
    private ContentsService contentsService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/admin/contents")
    public ContentResponse createContents(CreateContentRequest contentRequest){ // user 다시 정리되면 jwt 확인 후 사용 가능
        return contentsService.createContent(contentRequest);
    }

    @GetMapping("/admin/contents")
    public ContentsResponse getContents() {
        return contentsService.getContents();
    }

    @GetMapping("/contents")
    public ContentsResponse getContents(GetContentsByEmotionRequest contentRequest) {
        return contentsService.getContentsByEmotion(contentRequest);
    }
}
