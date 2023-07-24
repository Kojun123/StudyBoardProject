package com.blog.project.controller;

import com.blog.project.domain.Users;
import com.blog.project.dto.board.BoardDto;
import com.blog.project.dto.comment.CommentDto;
import com.blog.project.dto.user.UserDto;
import com.blog.project.exception.BadRequest;
import com.blog.project.service.BoardService;
import com.blog.project.service.CommentService;
import com.blog.project.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;
    private final UserService userService;
    private final CommentService commentService;

    @GetMapping(value = {"/","/board"}) // 게시글 리스트 페이지
    public String boardList(Model model, @RequestParam(value = "page", defaultValue = "0")Integer page) {
        Page<BoardDto> boards = boardService.getBoardList(page);
        model.addAttribute("paging",boards);
        return "board/list";
    }

    @GetMapping("/board/search")
    public String boardSearchList(Model model,@RequestParam(value = "keyword",required = false) String keyword,@RequestParam(value = "page", defaultValue = "0")Integer page,
                                  @RequestParam(value = "searchType", required = false) String searchType){
        Page<BoardDto> boards = boardService.getBoardSearchList(keyword,searchType,page);
        model.addAttribute("paging",boards);
        return "board/list";
    }



    @GetMapping("/board/saveBoard") // 게시글 작성 페이지
    @PreAuthorize("isAuthenticated()") // 로그인 되어있지 않은 경우 로그인 페이지로 이동, 로그인 후에는 다시 돌아오게됨.
    public String saveBoard(Model model){
        model.addAttribute("boardDto",new BoardDto());
        return "board/save";
    }

    @PostMapping("/board") // [post]게시글 작성
    @PreAuthorize("isAuthenticated()")
    public String Save(@Valid BoardDto boardDto, BindingResult bindingResult, Model model, Principal principal){

        if (bindingResult.hasErrors()) {
            return "board/save";
        }

        UserDto user = userService.getUser(principal.getName());
        boardService.boardSave(boardDto,user);
        model.addAttribute("message","글 작성 성공");
        model.addAttribute("url","/board");
        return "board/writeSuccess";
    }

    @GetMapping("/board/detail/{id}") // 게시글 상세 페이지
    public String boardDetail(@PathVariable Long id, Model model,HttpServletRequest req, HttpServletResponse res){

        BoardDto boardDto = boardService.getBoard(id);
        List<CommentDto> comments = boardDto.getComment();

        Cookie oldCookie = null;
        Cookie[] cookies = req.getCookies(); // request 객체에서 쿠키 가져옴.
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("boardView")) { // 쿠키가 비어 있지 않다면 반복문으로 돌리면서 boardView 이름의 쿠키가 있나 확인 있으면 대입
                    oldCookie = cookie;
                }
            }
        }

        if (oldCookie != null) {
            if (!oldCookie.getValue().contains("["+ id.toString() +"]")) { // cookie가 null이 아니고 cookie의 값중 id가 없다면 조회수를 올리고 쿠키 유지 시간을 갱신한다.
                boardService.viewCount(id);
                oldCookie.setValue(oldCookie.getValue() + "_[" + id + "]");
                oldCookie.setPath("/");
                oldCookie.setMaxAge(60 * 60 * 24);
                res.addCookie(oldCookie);
            }
        } else {
            boardService.viewCount(id);
            Cookie newCookie = new Cookie("boardView", "[" + id + "]"); // cookie 값이 없다면 boardView 라는 이름의 새로운 cookie 를 생성해주고 id 값을 추가하고 쿠키 유지 시간을 추가한다.
            newCookie.setPath("/");
            newCookie.setMaxAge(60 * 60 * 24);
            res.addCookie(newCookie);
        }


        model.addAttribute("comments",comments);
        model.addAttribute("board", boardDto);
        return "board/detail";
    }

    @GetMapping("/board/edit/{id}") // 게시글 수정 페이지
    @PreAuthorize("isAuthenticated()")
    public String BoardEdit(@PathVariable Long id, Model model,Principal principal){
        BoardDto board = boardService.getBoard(id);
        if (!board.getUser().getUserName().equals(principal.getName())) {
            throw new BadRequest("user", "수정 권한이 없습니다.");
        }
        model.addAttribute("board",board);
        return "board/edit";
    }

    @PatchMapping("/board/edit/{id}")
    @PreAuthorize("isAuthenticated()") // 로그인 확인
    public String Edit(@PathVariable Long id, BoardDto board, Principal principal){
        BoardDto boardDto = boardService.getBoard(id); // 해당 객체를 통해 유저 권한 조회

        if (!boardDto.getUser().getUserName().equals(principal.getName())){
            throw new BadRequest("수정 권한이 없습니다.");
        }

        boardService.edit(id,board); // board = 수정할 title, content 값만 가지고 있음.
        return String.format("redirect:/board/detail/%s",id);
    }

    @DeleteMapping("/board/{id}") // 게시글 삭제
    @PreAuthorize("isAuthenticated()")
    public String deleteBoard(@PathVariable Long id,Principal principal){
        BoardDto board = boardService.getBoard(id);
        if (!board.getUser().getUserName().equals(principal.getName())){
            throw new BadRequest("삭제 권한이 없습니다.");
        }
        boardService.deleteBoard(id);
        return "redirect:/";
    }






}
