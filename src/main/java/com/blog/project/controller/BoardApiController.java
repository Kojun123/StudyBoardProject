package com.blog.project.controller;


import com.blog.project.dto.board.BoardDto;
import com.blog.project.dto.comment.CommentDto;
import com.blog.project.dto.user.UserDto;
import com.blog.project.exception.BadRequest;
import com.blog.project.repository.BoardRepository;
import com.blog.project.service.BoardService;
import com.blog.project.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RequestMapping("/api")
@RestController
@RequiredArgsConstructor

public class BoardApiController {

    private final BoardService boardService;
    private final UserService userService;
    private final BoardRepository boardRepository;

    @GetMapping("/board/{id}") // READ
    public ResponseEntity read(@PathVariable Long id){
        return ResponseEntity.ok(boardService.getBoard(id));
    }

    @PostMapping("/board") // CREATE
    public ResponseEntity save(@RequestBody BoardDto boardDto){ // Principal 인증 학습 후 변경 예정 임시로 user 객체 만들어서 사용
        UserDto userDto = userService.getUser("apitester"); // apitester , 123
        return ResponseEntity.ok(boardService.boardSave(boardDto,userDto));
    }

    @PutMapping("/board/{id}") // UPDATE
    public ResponseEntity update(@PathVariable Long id, @RequestBody BoardDto boardDto){
        boardService.edit(id,boardDto);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/board/{id}") // DELETE
    public ResponseEntity delete(@PathVariable Long id){
        BoardDto board = boardService.getBoard(id);
        boardService.deleteBoard(id);
        return ResponseEntity.ok(id);
    }





}
