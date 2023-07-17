package com.blog.project.domain;


import com.blog.project.dto.user.UserDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "users")
public class Users extends BaseTime{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String userName;

    @JsonIgnore
    private String password;

    @Column(unique = true)
    private String email;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Comment> comment;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Board> board;


    @Builder
    public Users(Long id, String userName, String password, String email) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.email = email;
    }

    public UserDto toUserDto(){
        UserDto userDto = UserDto.builder().
                id(id)
                .userName(userName)
                .password(password)
                .email(email)
                .build();
        return userDto;
    }

    public void passwordUpdate(String password){
        this.password = password;
    }
}
