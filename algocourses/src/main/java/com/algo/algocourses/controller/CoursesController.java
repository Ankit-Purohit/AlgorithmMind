package com.algo.algocourses.controller;


import com.algo.algocourses.dto.CoursesDto;
import com.algo.algocourses.services.CoursesServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/algocourses")
public class CoursesController {
    @Autowired
    private CoursesServiceImpl coursesService;
    // adding course by admin
    @PostMapping()
    public ResponseEntity<CoursesDto> createCourse(@RequestBody CoursesDto coursesDto){
       CoursesDto coursesDto1= coursesService.createCourse(coursesDto);
       return new ResponseEntity<>(coursesDto1, HttpStatus.CREATED);
    }

    // deleting course by admin
    @DeleteMapping("/{courseId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String deleteCourseById(@PathVariable Long courseId) {
       String str=coursesService.deleteCourseById(courseId);
       return str;
    }

    // update course by admin
    @PutMapping("/{courseId}")
    public ResponseEntity<CoursesDto> updateCourseById(@PathVariable Long courseId,CoursesDto updatedCourse){
        return new ResponseEntity<>(coursesService.updateCourseById(courseId,updatedCourse),HttpStatus.OK);
    }

    // getting course By Id
    @GetMapping("/{courseId}")
    public ResponseEntity<CoursesDto> getCourseById(@PathVariable Long courseId){
        return new ResponseEntity<>(coursesService.getCourseById(courseId),HttpStatus.OK);
    }

    // getting all courses
    @GetMapping()
    public ResponseEntity<List<CoursesDto>> getAllCourses(){
        return new ResponseEntity<>(coursesService.getAllCourses(),HttpStatus.OK);
    }







}
