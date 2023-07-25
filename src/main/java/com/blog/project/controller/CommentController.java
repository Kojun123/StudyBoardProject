package com.blog.project.controller;

import com.blog.project.domain.Board;
import com.blog.project.domain.Comment;
import com.blog.project.domain.Users;
import com.blog.project.dto.comment.CommentDto;
import com.blog.project.dto.user.UserDto;
import com.blog.project.exception.BadRequest;
import com.blog.project.exception.UserNotFound;
import com.blog.project.service.BoardService;
import com.blog.project.service.CommentService;
import com.blog.project.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@Slf4j
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;
    private final BoardService boardService;
    private final UserService userService;

    @PostMapping("/board/{id}/comment") // 댓글 작성
    @PreAuthorize("isAuthenticated()")
    public String CreateBoardComment(@PathVariable("id") Long id, @Valid CommentDto comment, Principal principal){
        UserDto user = userService.getUser(principal.getName());
        if(comment.getUser() == null){
            throw new BadRequest("댓글 작성 권한이 없습니다.");
        }
        commentService.CommentSave(id,comment,user);
        return String.format("redirect:/board/detail/%s",id);
    }

    @PatchMapping("/comment/edit/{id}") // 댓글 수정
    @PreAuthorize("isAuthenticated()")
    public String EditBoardComment(@PathVariable("id") Long id,CommentDto comment,Principal principal){
        CommentDto commentDto = commentService.getComment(id);
        if (!commentDto.getUser().getUserName().equals(principal.getName())){
            throw new BadRequest("댓글 수정 권한이 없습니다.");
        }
        commentService.CommentEdit(id,comment);
        return String.format("redirect:/board/detail/%s", commentDto.getBoard().getId());

    }

    @DeleteMapping("/comment/delete/{id}") // 댓글 삭제
    @PreAuthorize("isAuthenticated()")
    public String DeleteComment(@PathVariable("id") Long id,Principal principal){
        CommentDto comment = commentService.getComment(id);
        if (!comment.getUser().getUserName().equals(principal.getName())){
            throw new BadRequest("댓글 삭제 권한이 없습니다.");
        }
        commentService.CommentDelete(id);
        return String.format("redirect:/board/detail/%s",comment.getBoard().getId());
    }



}
