package com.school.Exception;

public class BookException extends  RuntimeException{

    public BookException(Long id){
        super("Could not find cart with id: "+id);
    }
}
