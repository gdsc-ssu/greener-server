package com.gdsc.greener.repository;

import com.gdsc.greener.domain.Diary;
import com.gdsc.greener.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface DiaryRepository extends JpaRepository<Diary, Long> {
    List<Diary> findAllByUserAndCreatedAtBetween(User user, LocalDate start, LocalDate end);
    List<Diary> findAllByCreatedAtBetween(LocalDate start, LocalDate end);
    Optional<Diary> findByUserAndCreatedAt(User user, LocalDate createdAt);
    Optional<Diary> findByCreatedAt(LocalDate createdAt);
    Optional<Diary> findByIdAndUser(Long id, User user);
}
