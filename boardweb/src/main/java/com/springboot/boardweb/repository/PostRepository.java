package com.springboot.boardweb.repository;

import com.springboot.boardweb.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository <Post, Long> {
    List<Post> id(Long id);
    // save(), findById(), deleteById()

}
