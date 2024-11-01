package com.algo.algocourses.controller;


import com.algo.algocourses.dto.CoursesDto;
import com.algo.algocourses.services.CoursesService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/algo-courses")
@Validated
public class CoursesController {

    private final CoursesService coursesService;

    public CoursesController(CoursesService coursesService) {
        this.coursesService = coursesService;
    }

    // adding course by admin
    @PostMapping
    public ResponseEntity<CoursesDto> createCourse(@Valid @RequestBody CoursesDto coursesDto) {
        CoursesDto savedCourse = coursesService.createCourse(coursesDto);
        return new ResponseEntity<>(savedCourse, HttpStatus.CREATED);
    }

    // deleting course by admin
    @DeleteMapping("/{courseId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String deleteCourseById(@PathVariable Long courseId) {
        return coursesService.deleteCourseById(courseId);
    }

    // update course by admin
    @PutMapping("/{courseId}")
    public ResponseEntity<CoursesDto> updateCourseById(@NotNull @PathVariable Long courseId,
                                                       @Valid @RequestBody CoursesDto updatedCourse) {
        return new ResponseEntity<>(coursesService.updateCourseById(courseId, updatedCourse), HttpStatus.OK);
    }

    // getting course By Id
    @GetMapping("/{courseId}")
    public ResponseEntity<CoursesDto> getCourseById(@PathVariable Long courseId) {
        return new ResponseEntity<>(coursesService.getCourseById(courseId), HttpStatus.OK);
    }

    // getting all courses
    @GetMapping()
    public ResponseEntity<List<CoursesDto>> getAllCourses() {
        return new ResponseEntity<>(coursesService.getAllCourses(), HttpStatus.OK);
    }


}
