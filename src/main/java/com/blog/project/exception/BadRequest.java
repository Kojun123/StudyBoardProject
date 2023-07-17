package com.blog.project.exception;

public class BadRequest extends GlobalException{

    public BadRequest(String message) {
        super(message);
    }

    public BadRequest(String fieldName, String message){
        super(message);
        addValidation(fieldName, message);
    }

    @Override
    public int getStatusCode() {
        return 400;
    }
}
