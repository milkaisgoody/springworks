package com.springboot.boardweb.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter   // 폼 값(name=username, name=password)을 setter로 채운다
public class AddUserRequest {
    private String username;
    private String password;
}
