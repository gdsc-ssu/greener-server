package com.gdsc.greener.repository;

import com.gdsc.greener.domain.Contents;
import com.gdsc.greener.domain.Emotion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContentsRepository extends JpaRepository<Contents, String> {
    List<Contents> findAllByEmotion(Emotion emotion);
}
