package com.springboot.boardweb.repository;

import com.springboot.boardweb.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);   // 로그인할 때 아이디로 조회
    boolean existsByUsername(String username);        // 회원가입할 때 중복 확인
}
