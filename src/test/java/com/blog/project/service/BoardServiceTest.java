package com.blog.project.service;

import com.blog.project.domain.Board;
import com.blog.project.domain.Comment;
import com.blog.project.domain.Users;
import com.blog.project.dto.comment.CommentDto;
import com.blog.project.dto.user.UserCreateForm;
import com.blog.project.exception.PostNotFound;
import com.blog.project.repository.BoardRepository;
import com.blog.project.dto.board.BoardDto;
import com.blog.project.repository.CommentRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class BoardServiceTest {

    @Autowired
    private BoardService boardService;
    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private CommentRepository commentRepository;

    BoardDto boardDto = null;
    Users user = null;
    List<CommentDto> commentDtoList = new ArrayList<>();


    @BeforeEach
    void beforeEach(){


        boardDto = BoardDto.builder()
                .title("제목입니다.")
                .content("내용입니다.")
                .comment(commentDtoList)
                .build();

        UserCreateForm userForm = new UserCreateForm();
        userForm.setUserName("tester1");
        userForm.setEmail("tester@gmail.com");
        userForm.setPassword1("1234");
        user = userService.createUser(userForm.getUserName(), userForm.getEmail(), userForm.getPassword1());
    }

    @AfterEach
    void clean(){
        boardRepository.deleteAll();
    }

    @Test
    @DisplayName("글 작성 잘되는지 확인.")
    void test1(){
        // given(위 beforeEach 에서 생성해 주었다.)
        // when
        boardService.boardSave(boardDto,user);

        // then
        Assertions.assertEquals(1L,boardRepository.count());
        Board board = boardRepository.findAll().get(0);
        assertEquals("제목입니다.",board.getTitle());
        assertEquals("내용입니다.",board.getContent());
    }

    @Test
    @DisplayName("글 1개 조회")
    void test2(){
        //given
        //when

        Board board = boardService.boardSave(boardDto,user);
        BoardDto getboard = boardService.getBoard(board.getId());

        //then
        assertEquals(1L,boardRepository.count());
        assertEquals("제목입니다.",getboard.getTitle());
        assertEquals("내용입니다.",getboard.getContent());
    }

    @Test
    @DisplayName("글 제목 수정이 잘되는지 확인.")
    void test3(){
        //given

        Board board = boardService.boardSave(boardDto,user);
        BoardDto boardEdit = BoardDto.builder()
                .title("수정한 제목입니다.")
                .build();
        //when
        boardService.edit(board.getId(),boardEdit);
        //then
        Board changeBoard = boardRepository.findById(board.getId()).orElseThrow(PostNotFound::new);
        assertEquals("수정한 제목입니다.",changeBoard.getTitle());
    }

    @Test
    @DisplayName("존재하지 않는 글에 대한 삭제")
    void test4(){
        //given
        Board board = Board.builder()
                .title("제목입니다.")
                .content("내용입니다.")
                .build();
        boardRepository.save(board);
        //then
        assertThrows(PostNotFound.class, () -> {
            boardService.deleteBoard(board.getId()+1L); // 의도적으로 아이디 값을 더해줘서 없는 게시물을 삭제 시도
        });
    }



}