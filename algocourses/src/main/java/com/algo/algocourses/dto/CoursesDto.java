package com.algo.algocourses.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;


@Data
public class CoursesDto {
    @NotNull
    private Long courseId;
    @NotBlank
    private String courseName;
    @NotBlank
    private String description;
    @NotNull
    private String courseDuration;
    @NotNull
    private BigDecimal price;
    private String courseImage;

}
