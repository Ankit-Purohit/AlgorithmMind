package com.algo.algocourses.services;


import com.algo.algocourses.exceptions.CourseNotFoundException;
import com.algo.algocourses.dto.CoursesDto;
import com.algo.algocourses.entity.Courses;
import com.algo.algocourses.repository.CoursesRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CoursesServiceImpl implements CoursesService {
    private final CoursesRepository coursesRepository;
    private final ModelMapper modelMapper;

    public CoursesServiceImpl(CoursesRepository coursesRepository, ModelMapper modelMapper) {
        this.coursesRepository = coursesRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public CoursesDto createCourse(CoursesDto courseDto) {

        Courses courses = modelMapper.map(courseDto, Courses.class);
        Courses savedCourse = coursesRepository.save(courses);
        // Convert Entity back to DTO to return the saved course
        return modelMapper.map(savedCourse, CoursesDto.class);
    }

    @Override
    public CoursesDto updateCourseById(Long courseId, CoursesDto updatedCourseDto) {
        Optional<Courses> courses = coursesRepository.findById(courseId);
        if (courses.isPresent()) {
            Courses existedCourse = courses.get();
            existedCourse.setCourseName(updatedCourseDto.getCourseName());
            existedCourse.setCourseDuration(updatedCourseDto.getCourseDuration());
            existedCourse.setCourseImage(updatedCourseDto.getCourseImage());
            existedCourse.setPrice(updatedCourseDto.getPrice());
            existedCourse.setDescription(updatedCourseDto.getDescription());
            return modelMapper.map(existedCourse, CoursesDto.class);
        } else {
            throw new CourseNotFoundException("course with given Id is not founded");
        }
    }

    @Override
    public String deleteCourseById(Long courseId) {
        Optional<Courses> courses = coursesRepository.findById(courseId);
        if (courses.isPresent()) {
            Courses existedCourse = courses.get();
            coursesRepository.delete(existedCourse);
            return "course deleted successfully";
        } else {
            throw new CourseNotFoundException("course with given Id is not founded");
        }
    }

    @Override
    public List<CoursesDto> getAllCourses() {
        List<Courses> courses = coursesRepository.findAll();
        return courses.stream().map(course -> modelMapper.map(course, CoursesDto.class)).collect(Collectors.toList());

    }

    @Override
    public CoursesDto getCourseById(Long courseId) {
        Optional<Courses> optionalCourse = coursesRepository.findById(courseId);
        if (optionalCourse.isPresent()) {
            return modelMapper.map(optionalCourse.get(), CoursesDto.class);
        } else {
            throw new CourseNotFoundException("Course not found with ID: " + courseId);
        }
    }
}
