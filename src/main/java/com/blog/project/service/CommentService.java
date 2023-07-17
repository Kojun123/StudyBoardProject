package com.blog.project.service;


import com.blog.project.domain.Board;
import com.blog.project.domain.Comment;
import com.blog.project.domain.Users;
import com.blog.project.dto.comment.CommentDto;
import com.blog.project.dto.user.UserDto;
import com.blog.project.exception.CommentNotFound;
import com.blog.project.exception.PostNotFound;
import com.blog.project.repository.BoardRepository;
import com.blog.project.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;


    public void CommentSave(Long id, CommentDto commentDto, UserDto user){ // 댓글 작성
        Board board = boardRepository.findById(id).orElseThrow(PostNotFound::new);
        commentDto.setUser(user);
        commentDto.setBoard(board.toBoardDto());
        Comment comment = commentDto.toEntity();
        commentRepository.save(comment);
    }

    public CommentDto getComment(Long id){ // 댓글 조회
        Comment comment = commentRepository.findById(id).orElseThrow(CommentNotFound::new);
        return new CommentDto(comment);
    }

    public void CommentEdit(Long id,CommentDto commentDto){
        Comment comment = commentRepository.findById(id).orElseThrow(CommentNotFound::new);
        comment.updateComment(
                commentDto.getBoardComment() != null ? commentDto.getBoardComment() : comment.getBoardComment() // null 이면 업데이트 하지 않음(기존의 것을 그대로 사용함.)
        ); // 더티 체크사용으로 save 호출 하지않음.
    }


    public void CommentDelete(Long id){ // 댓글 삭제
        Comment comment = commentRepository.findById(id).orElseThrow(CommentNotFound::new);
        commentRepository.deleteById(comment.getId());
    }





}
