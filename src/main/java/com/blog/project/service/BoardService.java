package com.blog.project.service;

import com.blog.project.domain.Board;
import com.blog.project.dto.board.BoardDto;
import com.blog.project.dto.user.UserDto;
import com.blog.project.exception.PostNotFound;
import com.blog.project.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class BoardService {

    private final BoardRepository boardRepository;
    private final UserService userService;

    @Transactional(readOnly = true)
    public Page<BoardDto> getBoardList(Integer page) { // 전체 리스트 반환

        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("createdDate")); // 작성시간 기준 내림차순 정렬
        Pageable pageable = PageRequest.of(page, 10,Sort.by(sorts)); // 현재 페이지 번호, 보여줄 페이지 수, 정렬
        // return boardRepository.findAll(pageable).map(board -> new BoardDto(board.getId(),board.getTitle(),board.getContent()));  dto로 변환해서 반환
        return boardRepository.findAll(pageable).map(BoardDto::new); // 생성자 참조 표현식으로 간략하게 변경가능
    }

    @Transactional(readOnly = true) 
    public List<BoardDto> getAll(){ // 페이징 하지 않고 전체 게시물 반환
        List<Board> board = boardRepository.findAll();
        return board.stream().map(BoardDto::new).collect(Collectors.toList());
    }

    public Page<BoardDto> getBoardSearchList(String keyword,String searchType, Integer page){ // 검색 결과 리스트 반환
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("createdDate"));
        Pageable pageable = PageRequest.of(page,10,Sort.by(sorts));
        switch (searchType){
            case "T" : return boardRepository.findByTitleContaining(keyword,pageable).map(BoardDto::new);
            case "C" : return boardRepository.findBycontentContaining(keyword,pageable).map(BoardDto::new);
            case "W" : return boardRepository.findByUserContaining(keyword,pageable).map(BoardDto::new);
            default: return null;
        }


    }

    @Transactional(readOnly = true)
    public BoardDto getBoard(Long id) {
        Board board = boardRepository.findById(id).orElseThrow(PostNotFound::new);
        return new BoardDto(board);
    }


    public Board boardSave(BoardDto boardDto, UserDto user){
        boardDto.setUser(user);
        return boardRepository.save(boardDto.toEntity());
    }

    public Board boardSaveApiTest(BoardDto boardDto){ // principal 해결 하면 바꾸기
        UserDto user = userService.getUser("tester1");
        boardDto.setUser(user);
        return boardRepository.save(boardDto.toEntity());
    }


    public void edit(Long id, BoardDto boardEdit){
        Board board = boardRepository.findById(id).orElseThrow(PostNotFound::new);
        board.update(
                boardEdit.getTitle() != null ? boardEdit.getTitle() : board.getTitle(),
                boardEdit.getContent() != null ? boardEdit.getContent() : board.getContent()
        ); //세이브 안해도 더티체킹으로 바꿔준다.
    }

    public void deleteBoard(Long id) {
        Board board = boardRepository.findById(id).orElseThrow(PostNotFound::new);
        boardRepository.deleteById(board.getId());
    }

    public void viewCount(Long id){
        boardRepository.viewCount(id);
    }


}
