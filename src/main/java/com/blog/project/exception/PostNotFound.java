package com.blog.project.exception;

/**
 * status : 404
 */
public class PostNotFound extends GlobalException{

    private static String MESSAGE = "존재하지 않는 글입니다.";

    public PostNotFound(){
        super(MESSAGE);
    }

    @Override
    public int getStatusCode() {
        return 404;
    }
}
