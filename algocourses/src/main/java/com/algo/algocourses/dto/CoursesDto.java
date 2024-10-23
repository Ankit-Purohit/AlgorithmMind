package com.algo.algocourses.dto;

import lombok.Data;

import java.math.BigDecimal;


@Data
public class CoursesDto {
    private Long courseId;
    private String courseName;
    private String description;
    private String courseDuration;
    private BigDecimal price;
    private String courseImage;

}
