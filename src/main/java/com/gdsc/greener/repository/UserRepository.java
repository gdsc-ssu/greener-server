package com.gdsc.greener.repository;

import com.gdsc.greener.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    // 이메일로 검색
    Optional<User> findByEmail(String email);
}
