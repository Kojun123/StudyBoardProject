package com.blog.project.dto.user;

import com.blog.project.domain.Users;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {

    private Long id;
    private String userName;
    @JsonIgnore
    private String password;
    private String email;
    @JsonIgnore
    private LocalDateTime createdDate;

    @Builder
    public UserDto(Long id, String userName, String password, String email, LocalDateTime createdDate) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.createdDate = createdDate;
        this.email = email;
    }

    public Users toEntity(){
        Users user = Users.builder()
                .id(id)
                .userName(userName)
                .password(password)
                .email(email)
                .build();
        return user;
    }
}
