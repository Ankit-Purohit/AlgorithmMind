package com.algo.algotopics.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TopicDto {
    private Long topicId;
    private Long courseId;
    private String topicName;
    private String videoUrl;
    private String topicDescription;
}
