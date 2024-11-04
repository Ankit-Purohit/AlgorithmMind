package com.algo.algocourses.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name="courses")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Courses {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courseId;
    private String courseName;
    private String description;
    private String courseDuration;
    private BigDecimal price;
    private String courseImage;
}
