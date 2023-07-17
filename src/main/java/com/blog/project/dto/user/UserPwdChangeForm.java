package com.blog.project.dto.user;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class UserPwdChangeForm {

    @NotEmpty(message = "비밀번호는 필수 항목입니다.")
    @NotBlank
    private String nowPwd;

    @NotEmpty(message = "변경할 비밀번호는 필수 항목입니다.")
    @NotBlank
    private String changePwd1;

    @NotEmpty(message = "변경할 비밀번호 확인은 필수 항목입니다.")
    @NotBlank
    private String changePwd2;
}
