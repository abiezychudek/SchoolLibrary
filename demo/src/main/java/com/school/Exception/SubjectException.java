package com.school.Exception;

public class SubjectException extends RuntimeException{
    public SubjectException(Long id){
        super("Cannot find subject with id: "+id);
    }
}
