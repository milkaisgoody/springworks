package com.springboot.boardweb.service;

import com.springboot.boardweb.domain.User;
import com.springboot.boardweb.dto.AddUserRequest;
import com.springboot.boardweb.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;   // SecurityConfig의 빈

    // 회원가입
    public Long save(AddUserRequest request) {
        if (request.getUsername() == null || request.getUsername().isBlank()
                || request.getPassword() == null || request.getPassword().isBlank()) {
            throw new IllegalArgumentException("아이디와 비밀번호를 입력하세요.");
        }
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new IllegalArgumentException("이미 사용 중인 아이디입니다.");
        }
        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))   // 암호화
                .role("USER")
                .build();
        return userRepository.save(user).getId();
    }
}
