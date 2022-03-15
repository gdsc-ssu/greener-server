package com.gdsc.greener.repository;

import com.gdsc.greener.domain.Account;
import com.gdsc.greener.domain.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    // 이메일로 검색
    Optional<Account> findByEmail(String email);
    Optional<Account> findByEmailAndStateAndToken(String email, UserStatus state, String token);
}
