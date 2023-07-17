package com.blog.project.repository;

import com.blog.project.domain.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BoardRepository extends JpaRepository<Board, Long>,BoardRepositoryCustom {

    Page<Board> findAll(Pageable pageable);

    @Modifying
    @Query("update Board b set b.view = b.view + 1 where b.id = :id")
    int viewCount(@Param("id") Long id);

    Page<Board> findByTitleContaining(String keyword, Pageable pageable);

    Page<Board> findBycontentContaining(String keyword, Pageable pageable);

    @Query("select b from Board b join b.user u where u.userName like %:keyword%")
    Page<Board> findByUserContaining(@Param("keyword") String keyword,Pageable pageable);
}
