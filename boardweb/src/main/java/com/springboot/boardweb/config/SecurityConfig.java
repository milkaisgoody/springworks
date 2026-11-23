package com.springboot.boardweb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http){
        http
                .csrf(AbstractHttpConfigurer::disable) //CSRF 보호끄기
                // ① 경로별 권한
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/posts", "/posts/*",
                                "/signup", "/login").permitAll()
                        .requestMatchers("/css/**", "/js/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/posts/**").permitAll()
                        .anyRequest().authenticated()  //나머지는 로그인 필요
                )
                // ② 폼 로그인
                .formLogin(form -> form
                        .loginPage("/login")                  // 우리가 만든 로그인 화면
                        .defaultSuccessUrl("/posts")          // 로그인 성공 → 글 목록
                )
                // ③ 로그아웃
                .logout(logout -> logout
                        .logoutSuccessUrl("/")                // 로그아웃 → 메인
                );
        return http.build();
    }

    // 비밀번호 암호화 도구(UserService에서 주입받아 사용)
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
