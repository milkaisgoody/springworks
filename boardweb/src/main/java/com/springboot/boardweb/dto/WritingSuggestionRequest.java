package com.springboot.boardweb.dto;

import java.util.Map;

public record WritingSuggestionRequest(String title, String content, String question) {
    // 압축 생성자 — 빈 값(null)이면 ""로 (Map.of()는 null 값을 넣으면 NullPointerException)
    public WritingSuggestionRequest {
        title = (title == null) ? "" : title;
        content = (content == null) ? "" : content;
        question = (question == null) ? "" : question;
    }

    // 템플릿 자리 {title} {content} {question} {format}에 넣을 값
    public Map<String, Object> toMap(String format) {
        return Map.of("title", title, "content", content,
                "question", question, "format", format);
    }
}
