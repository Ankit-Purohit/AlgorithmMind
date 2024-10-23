package com.algo.algocourses.ExceptionsHandler;

public class CourseNotFoundException  extends RuntimeException{
    public CourseNotFoundException(String message) {
        super(message);
    }

}
