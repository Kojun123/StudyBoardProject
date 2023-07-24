package com.blog.project.controller;


import com.blog.project.domain.Users;
import com.blog.project.dto.board.BoardDto;
import com.blog.project.dto.user.UserCreateForm;
import com.blog.project.dto.user.UserDto;
import com.blog.project.dto.user.UserPwdChangeForm;
import com.blog.project.repository.UserRepository;
import com.blog.project.service.BoardService;
import com.blog.project.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final BoardService boardService;
    private final UserRepository userRepository;

    @GetMapping("/signup")
    public String signup(UserCreateForm userCreateForm){
        return "user/signup";
    }

    @PostMapping("/signup") // 회원 가입
    public String signup(@Valid UserCreateForm userCreateForm, BindingResult bindingResult){

        if (bindingResult.hasErrors()) {
            return "user/signup";
        }

        if (!userCreateForm.getPassword1().equals(userCreateForm.getPassword2())){ // 비밀번호와 비밀번호 확인이 서로 일치하지 않는다면
            bindingResult.rejectValue("password2","passwordInCorrect","패스워드가 일치하지 않습니다.");
            return "user/signup";
        }

        if (userService.checkEmailDuplicated(userCreateForm.getEmail()) == true ){
            bindingResult.rejectValue("email","emailDuplicatedError","이미 가입되어 있는 이메일입니다.");
            return "user/signup";
        }

        if (userService.checkIdDuplicated(userCreateForm.getUserName()) == true){
            bindingResult.rejectValue("userName","IdDuplicatedError", "이미 존재하는 Id 입니다.");
            return "user/signup";
        }

        userService.createUser(userCreateForm.getUserName(),userCreateForm.getEmail(),userCreateForm.getPassword1());
        return "redirect:/";
    }

    @GetMapping("/login")
    public String login(){
        return "user/login";
    }

    @GetMapping("/findPwd")
    public String changePwd(UserPwdChangeForm userPwdChangeForm){
        return "user/passwordFind";
    }

    @PostMapping("/findPwd") // 비밀번호 변경
    public String changePwd(@Valid UserPwdChangeForm userPwdChangeForm, BindingResult bindingResult, Principal principal){

        UserDto user = userService.getUser(principal.getName());

        if (bindingResult.hasErrors()) {
            return "user/passwordFind";
        }

        if (!passwordEncoder.matches(userPwdChangeForm.getNowPwd(),user.getPassword())){
            bindingResult.rejectValue("nowPwd","passwordNotMatch","현재 비밀번호가 일치하지 않습니다.");
            return "user/passwordFind";
        }

        if (!userPwdChangeForm.getChangePwd1().equals(userPwdChangeForm.getChangePwd2())){
            bindingResult.rejectValue("changePwd2","passwordNotMatch","비밀번호가 일치하지 않습니다.");
            return "user/passwordFind";
        }

        userService.changeUserPwd(user.getUserName(),userPwdChangeForm.getChangePwd2());
        return "user/login";
    }

    @GetMapping("/userinfo") // 회원 정보
    public String userInfo(Principal principal, Model model){

        UserDto user = userService.getUser(principal.getName());
        List<BoardDto> boardDtoList = boardService.getAll();
        model.addAttribute("user",user);
        return "user/userinfo";
    }

    @GetMapping("/postCreated") // 회원이 작성한 게시글 보는 페이지
    public String userCreatedPost(Model model, @RequestParam(value = "page", defaultValue = "0")Integer page,Principal principal){
        Page<BoardDto> boards = boardService.getBoardList(page);
        UserDto user = userService.getUser(principal.getName()); // 현재 세션에서 로그인 중인 유저

        model.addAttribute("user",user);
        model.addAttribute("paging",boards);
        return "user/postCreated";
    }

}
