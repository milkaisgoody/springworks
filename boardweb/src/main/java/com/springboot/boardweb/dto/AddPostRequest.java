package com.springboot.boardweb.dto;

import com.springboot.boardweb.domain.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Getter
public class AddPostRequest {
    private String title;
    private String content;
    //private String writer; //삭제

    // DTO를 엔티티로 만들어 주는 메서드, 글쓴이는 서버거 넘겨준다.
    public Post toEntity(String writer) {
        return Post.builder()
                .title(title)
                .content(content)
                .writer(writer)
                .build();
    }
}
