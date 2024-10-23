package com.algo.algocourses.services;


import com.algo.algocourses.ExceptionsHandler.CourseNotFoundException;
import com.algo.algocourses.dto.CoursesDto;
import com.algo.algocourses.entity.Courses;
import com.algo.algocourses.repository.CoursesRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CoursesServiceImpl implements CoursesServices {
    @Autowired
   private CoursesRepository coursesRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CoursesDto createCourse(CoursesDto courseDto) {

        Courses courses=modelMapper.map(courseDto, Courses.class);
        Courses savedCourse = coursesRepository.save(courses);
        // Convert Entity back to DTO to return the saved course
        CoursesDto savedCourseDto = modelMapper.map(savedCourse, CoursesDto.class);
        // Return the saved DTO
        return savedCourseDto;
    }

    @Override
    public CoursesDto updateCourseById(Long courseId,CoursesDto updatedCourseDto) {
      Optional<Courses> courses=coursesRepository.findById(courseId);

      if(courses.isPresent()){
          Courses courses1=courses.get();
          courses1.setCourseName(updatedCourseDto.getCourseName());
          courses1.setCourseDuration(updatedCourseDto.getCourseDuration());
          courses1.setCourseImage(updatedCourseDto.getCourseImage());
          courses1.setPrice(updatedCourseDto.getPrice());
          courses1.setDescription(updatedCourseDto.getDescription());
          return modelMapper.map(courses1,CoursesDto.class);
      }
    else{
        throw new CourseNotFoundException("course with given Id is not founded");
      }
    }

    @Override
    public String deleteCourseById(Long courseId) {
        Optional<Courses> courses=coursesRepository.findById(courseId);
        if(courses.isPresent()){
            Courses courses1=courses.get();
            coursesRepository.delete(courses1);
            return "course deleted successfully";
        }
        else{
            throw new CourseNotFoundException("course with given Id is not founded");
        }
    }

    @Override
    public List<CoursesDto> getAllCourses() {
       List<Courses> courses=coursesRepository.findAll();
        return courses.stream()
                .map(course -> modelMapper.map(course, CoursesDto.class))
                .collect(Collectors.toList());

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
