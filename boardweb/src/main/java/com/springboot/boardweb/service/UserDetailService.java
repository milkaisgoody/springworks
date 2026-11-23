package com.springboot.boardweb.service;

import com.springboot.boardweb.domain.User;
import com.springboot.boardweb.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserDetailService implements UserDetailsService {
    private final UserRepository userRepository;

    // 로그인할 때 시큐리티가 호출 — 아이디로 회원을 찾아 넘겨준다
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));

        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername())
                .password(user.getPassword())   // 암호화된 비밀번호
                .roles(user.getRole())          // USER → 권한 ROLE_USER
                .build();
    }
}
