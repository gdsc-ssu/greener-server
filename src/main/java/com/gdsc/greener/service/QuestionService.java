package com.gdsc.greener.service;

import com.gdsc.greener.domain.Question;
import com.gdsc.greener.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;
    public Question randomQuestion() {
        Long qty = questionRepository.count();
        int idx = (int)(Math.random() * qty);
        Page<Question> questionPage = questionRepository.findAll(PageRequest.of(idx, 1));
        Question q = null;
        if (questionPage.hasContent()) {
            q = questionPage.getContent().get(0);
        }
        return q;
    }
}
