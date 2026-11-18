package com.springboot.boardweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing //날짜가 자동으로 나오도록 해줌
@SpringBootApplication
public class BoardwebApplication {

	public static void main(String[] args) {
		SpringApplication.run(BoardwebApplication.class, args);
	}

}
