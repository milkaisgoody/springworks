package com.springboot.boardweb.service;

import com.springboot.boardweb.domain.Post;
import com.springboot.boardweb.dto.AddPostRequest;
import com.springboot.boardweb.dto.UpdatePostRequest;
import com.springboot.boardweb.repository.PostRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.resource.ResourceUrlEncodingFilter;

import java.util.List;

@AllArgsConstructor //생성자 주입
@Service
public class PostService {
    private PostRepository postRepository;

    //글 등록
    public Post save(AddPostRequest request){
        //게시글이 DB에 저장
        return postRepository.save(request.toEntity());
    }

    //글 목록
    public List<Post> findAll(){
        return postRepository.findAll();
    }

    //글 상세보기
    public Post findById(Long id){
        return postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found: " + id));
    }

    //글삭제
    public void delete(Long id){
        postRepository.deleteById(id);
    }

    //글수정
    @Transactional
    public Post update(Long id, UpdatePostRequest request){
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found: " + id));

        //글수정 (update 함수는 Post에 있음)
        post.update(request.getTitle(), request.getContent());
        return post;
    }
}
