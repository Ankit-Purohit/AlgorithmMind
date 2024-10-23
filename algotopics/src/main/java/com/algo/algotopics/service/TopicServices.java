package com.algo.algotopics.service;

import com.algo.algotopics.dto.TopicDto;
import com.algo.algotopics.entity.Topics;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TopicServices {

    // adding a topic of a particular course
    public TopicDto addTopic(TopicDto topicDto);

    // getting a topic by topic Id
    public TopicDto getTopic(Long topicId);

    // getting multiple topics (list of) by courseId

    public List<TopicDto> getTopicsByCourseId(Long courseId);

    // deleting a single topic
    public String deleteTopic(Long topicId);

    // deleting list of topics using courseId

    public String deleteTopicByCourseId(Long courseId);














}
