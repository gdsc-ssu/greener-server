package com.gdsc.greener.service;

import com.gdsc.greener.domain.Contents;
import com.gdsc.greener.repository.ContentsRepository;
import com.gdsc.greener.request.CreateContentRequest;
import com.gdsc.greener.request.GetContentsByEmotionRequest;
import com.gdsc.greener.response.ContentsResponse;
import com.gdsc.greener.response.ContentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ContentsService {
    @Autowired
    private ContentsRepository contentsRepository;

    public ContentResponse createContent(CreateContentRequest createContentRequest){ // user 다시 정리되면 jwt 확인 후 사용 가능
        return new ContentResponse(contentsRepository.save(
                Contents.builder()
                        .emotion(createContentRequest.getEmotion())
                        .url(createContentRequest.getUrl())
                        .build()
        ));
    }

    public ContentsResponse getContents() {
        return new ContentsResponse(contentsRepository.findAll());
    }

    public ContentsResponse getContentsByEmotion(GetContentsByEmotionRequest contentRequest) {
        return new ContentsResponse(contentsRepository.findAllByEmotion(contentRequest.getEmotion()));
    }
}
