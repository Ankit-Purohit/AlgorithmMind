package com.algo.algocourses.services;

import com.algo.algocourses.dto.CoursesDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface CoursesServices {
    // admin can add a course
    public CoursesDto createCourse(CoursesDto courseDto);
    // admin can update a course
    public CoursesDto updateCourseById(Long courseId,CoursesDto updatedCourseDto);
    // admin can delete a course
    public String deleteCourseById(Long courseId);


    //user perspective
    // get all the courses
    public List<CoursesDto> getAllCourses();
    // get courses by Id
    public CoursesDto getCourseById(Long courseId);




}
