package com.blog.project.dto.comment;

import com.blog.project.domain.Board;
import com.blog.project.domain.Comment;
import com.blog.project.dto.board.BoardDto;
import com.blog.project.dto.user.UserDto;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class CommentRequest {

    private Long id;
    @NotEmpty(message = "내용은 필수항목입니다.")
    private String boardComment;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    private Board board;
    private UserDto user;

    @Builder
    public CommentRequest(Long id, String boardComment, LocalDateTime createdDate, LocalDateTime modifiedDate, Board board, UserDto user) {
        this.id = id;
        this.boardComment = boardComment;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        this.board = board;
        this.user = user;
    }

    public CommentRequest(Comment comment){
        this.id = comment.getId();
        this.boardComment = comment.getBoardComment();
        this.board = comment.getBoard();
        this.user = comment.getUser().toUserDto();
        this.createdDate = comment.getCreatedDate();
        this.modifiedDate = comment.getModifiedDate();
    }
}
