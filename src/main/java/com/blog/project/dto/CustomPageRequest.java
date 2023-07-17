package com.blog.project.dto;


import org.springframework.data.domain.PageRequest;

import static org.springframework.data.domain.Sort.*;

public class CustomPageRequest {

    private int page = 1;
    private int size = 10;
    private Direction direction = Direction.DESC;

    public void setPage(int page){
        this.page = page <= 0 ? 1 : page;
    }

    public void setDirection(Direction direction){
        this.direction = direction;
    }

    public PageRequest of(){
        return PageRequest.of(page-1, size, direction,"create_date");
    }

}
