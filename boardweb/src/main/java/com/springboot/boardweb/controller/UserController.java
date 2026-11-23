package com.springboot.boardweb.controller;

import com.springboot.boardweb.dto.AddUserRequest;
import com.springboot.boardweb.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class UserController {
    private final UserService userService;

    // 회원가입 화면 → GET /signup
    @GetMapping("/signup")
    public String signupForm() {
        return "user/signup";
    }

    // 회원가입 처리 → POST /signup
    @PostMapping("/signup")
    public String signup(AddUserRequest request, Model model) {
        try {
            userService.save(request);
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());   // 오류 문구
            model.addAttribute("username", request.getUsername());
            return "user/signup";                            // 다시 가입 화면
        }
        return "redirect:/login?signup";   // 가입 성공 → 로그인 화면
    }

    // 로그인 화면 → GET /login (로그인 처리는 시큐리티가 한다)
    @GetMapping("/login")
    public String loginForm() {
        return "user/login";
    }
}
