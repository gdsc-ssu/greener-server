package com.gdsc.greener.repository;

import com.gdsc.greener.domain.Diary;
import com.gdsc.greener.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface DiaryRepository extends JpaRepository<Diary, Long> {
    List<Diary> findAllByAccountAndCreatedAtBetween(Account account, LocalDate start, LocalDate end);
    List<Diary> findAllByCreatedAtBetween(LocalDate start, LocalDate end);
    Optional<Diary> findByAccountAndCreatedAt(Account account, LocalDate createdAt);
    Optional<Diary> findByCreatedAt(LocalDate createdAt);
    Optional<Diary> findByIdAndAccount(Long id, Account account);
}
