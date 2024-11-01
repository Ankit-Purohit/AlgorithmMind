package com.algo.algocourses.services;

import com.algo.algocourses.dto.CoursesDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface CoursesService {
    // admin can add a course
     CoursesDto createCourse(CoursesDto courseDto);
    // admin can update a course
     CoursesDto updateCourseById(Long courseId,CoursesDto updatedCourseDto);
    // admin can delete a course
     String deleteCourseById(Long courseId);

    //user perspective
    // get all the courses
     List<CoursesDto> getAllCourses();
    // get courses by Id
     CoursesDto getCourseById(Long courseId);

}
