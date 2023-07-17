package com.blog.project.domain;


import com.blog.project.dto.comment.CommentDto;
import com.blog.project.dto.comment.CommentRequest;
import com.blog.project.dto.user.UserDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "comments")
public class Comment extends BaseTime{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String boardComment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "boardId")
    @JsonIgnore
    private Board board;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private Users user;

    @Builder
    public Comment(Long id, String boardComment, String createDate, String modifiedDate, Board board, UserDto user) {
        this.id = id;
        this.boardComment = boardComment;
        this.board = board;
        this.user = user.toEntity();
    }

    public Comment(CommentDto commentDto){
        this.id = commentDto.getId();
        this.boardComment = commentDto.getBoardComment();
        this.board = commentDto.getBoard().toEntity();
        this.user = commentDto.getUser().toEntity();
    }

    public void updateComment(String boardComment){
        this.boardComment = boardComment;
    }
}
