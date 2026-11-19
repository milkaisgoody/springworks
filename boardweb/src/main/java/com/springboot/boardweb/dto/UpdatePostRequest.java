package com.springboot.boardweb.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

//글 수정요청
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UpdatePostRequest {
    private String title;
    private String content;

}
