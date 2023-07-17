package com.blog.project.repository;

import com.blog.project.domain.Board;
import com.blog.project.dto.board.BoardSearch;
import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static com.blog.project.domain.QBoard.board;


@RequiredArgsConstructor
public class BoardRepositoryCustomImpl implements BoardRepositoryCustom{

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Page<Board> getList(BoardSearch boardSearch, Pageable pageable) {
        QueryResults<Board> result = jpaQueryFactory.selectFrom(board)
                .limit(boardSearch.getSize())
                .offset(boardSearch.getOffset())
                .orderBy(board.id.desc())
                .fetchResults();
        List<Board> content = result.getResults();
        Long count = result.getTotal();
        return new PageImpl<>(content,pageable,count);

    }
}
