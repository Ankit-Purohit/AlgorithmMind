package com.algo.algotopics.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity(name = "topics")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Topics {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long topicId;
    private Long courseId;
    private String topicName;
    private String videoUrl;
    private String topicDescription;
}
