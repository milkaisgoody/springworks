package com.springboot.boardweb.service;

import com.springboot.boardweb.domain.Post;
import com.springboot.boardweb.dto.AddPostRequest;
import com.springboot.boardweb.dto.UpdatePostRequest;
import com.springboot.boardweb.repository.PostRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.resource.ResourceUrlEncodingFilter;

import java.util.List;

@AllArgsConstructor //생성자 주입
@Service
public class PostService {
    private PostRepository postRepository;

    //글 등록
    public Post save(AddPostRequest request, String username){
        //게시글이 DB에 저장
        return postRepository.save(request.toEntity(username));
    }

    //글 목록
    public List<Post> findAll(){
        return postRepository.findAll();
    }

    //글 목록 + 검색
    public List<Post> search (String type, String keyword){
        if(keyword == null || keyword.isBlank()){
            return findAll();  // 검색어가 없으면 전체 목록
        }
        keyword = keyword.trim();

        return switch (type == null ? "title" : type){
            case "content" -> postRepository.findByContentContainingOrderByIdDesc(keyword);
            case "writer" -> postRepository.findByWriterContainingOrderByIdDesc(keyword);
            default -> postRepository.findByTitleContainingOrderByIdDesc(keyword);
        };
    }

    //글 상세보기
    public Post findById(Long id){
        return postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found: " + id));
    }

    //글삭제
    public void delete(Long id, String username, boolean isAdmin){
        Post post = findById(id); //상세보기 호출
        //글쓴이와 로그인한 사람이 일치하는지 확인
        if(!isAdmin){
            checkWriter(post,username);
        }
        postRepository.delete(post);
    }

    //글수정
    @Transactional
    public Post update(Long id, UpdatePostRequest request, String username){
        Post post = findById(id);
        checkWriter(post,username);

        //글수정 (update 함수는 Post에 있음)
        post.update(request.getTitle(), request.getContent());
        return post;
    }

    //글쓴이와 로그인한 사람 아이디 비교
    public void checkWriter(Post post, String username){
        if(!post.getWriter().equals(username)){
            throw new AccessDeniedException("본인이 쓴 글만 수정, 삭제할 수 있습니다.");
        }
    }
}
