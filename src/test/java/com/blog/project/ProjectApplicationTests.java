package com.blog.project;

import com.blog.project.domain.Users;
import com.blog.project.dto.board.BoardDto;
import com.blog.project.repository.BoardRepository;
import com.blog.project.repository.UserRepository;
import com.blog.project.service.BoardService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProjectApplicationTests {

	@Autowired
	BoardService boardService;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private BoardRepository boardRepository;

	@Test
	@DisplayName("페이징 잘 작동되는지 확인하기 위해 게시글 더미 작성")
	void test1() {

		for (int i = 1; i <= 200; i++) {
			String subject = String.format("게시글 입니다.:[%04d]", i);
			String content = "냉무";
			BoardDto boardDto = BoardDto.builder()
					.title(subject)
					.content(content)
					.build();
			boardRepository.save(boardDto.toEntity());
		}
	}
	}


