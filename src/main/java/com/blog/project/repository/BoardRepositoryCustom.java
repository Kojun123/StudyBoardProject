package com.blog.project.repository;

import com.blog.project.domain.Board;
import com.blog.project.dto.board.BoardSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BoardRepositoryCustom {

    Page<Board> getList(BoardSearch boardSearch,Pageable pageable);
}
