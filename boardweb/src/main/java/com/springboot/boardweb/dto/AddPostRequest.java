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
    private String writer;

    // DTO를 엔티티로 만들어 주는 메서드
    public Post toEntity() {
        return Post.builder()
                .title(title)
                .content(content)
                .writer(writer)
                .build();
    }
}
