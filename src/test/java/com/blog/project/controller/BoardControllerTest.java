package com.blog.project.controller;

import com.blog.project.domain.Board;
import com.blog.project.exception.PostNotFound;
import com.blog.project.repository.BoardRepository;
import com.blog.project.dto.board.BoardDto;
import com.blog.project.service.BoardService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class BoardControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private BoardService boardService;
    @Autowired
    private ObjectMapper objectMapper;

    @AfterEach
    void clean(){
        boardRepository.deleteAll();
    }

    @Test
    @DisplayName("db에 저장이 잘되는지 확인한다.")
    void test1() throws Exception{
        //given
        BoardDto boardDto = BoardDto.builder()
                .title("제목입니다.")
                .content("내용입니다.")
                .build();

        //expected
        mockMvc.perform(post("/board")
                .content(String.valueOf(boardDto))
        )
                .andDo(print());
        Board board = boardRepository.findAll().get(0);
        assertEquals("제목입니다.",board.getTitle());
        assertEquals("내용입니다.",board.getContent());

    }

    @Test
    @DisplayName("글 제목 수정 확인")
    void test2() throws Exception{
        //given
        Board board = Board.builder()
                .title("수정전 제목입니다.")
                .content("내용입니다.")
                .build();

        boardRepository.save(board);

        BoardDto boardEdit = BoardDto.builder()
                .title("수정한 제목입니다.")
                .build();
        //when
        boardService.edit(board.getId(),boardEdit);
        //then
        Board changeBoard = boardRepository.findById(board.getId()).orElseThrow(PostNotFound::new);
        assertEquals("수정한 제목입니다.",changeBoard.getTitle());

    }

}