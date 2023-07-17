package com.blog.project.dto.board;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardSearch {

    private static final int MAX_SIZE = 2000;


    private final Integer page = 1;


    private final Integer size = 10;

    public long getOffset(){
        return (long)(Math.max(1,page)-1) * Math.min(size,MAX_SIZE);
    }

}
