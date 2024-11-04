package com.algo.algotopics.controller;
import com.algo.algotopics.dto.TopicDto;
import com.algo.algotopics.service.TopicsServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@Controller
@RequestMapping("/topics")
public class TopicController {
    @Autowired
    private TopicsServicesImpl topicsServices;

    @PostMapping
    ResponseEntity<TopicDto> addTopic(@RequestBody TopicDto topicDto){
       TopicDto topicDto1=topicsServices.addTopic(topicDto);
       return new ResponseEntity<>(topicDto1, HttpStatus.CREATED);
    }

    // Get a specific topic by topic ID
    @GetMapping("/{topicId}")
    public ResponseEntity<TopicDto> getTopic(@PathVariable Long topicId) {
        TopicDto topicDto = topicsServices.getTopic(topicId);
        return new ResponseEntity<>(topicDto, HttpStatus.OK);
    }

    // Get all topics by course ID
    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<TopicDto>> getTopicsByCourseId(@PathVariable Long courseId) {
        List<TopicDto> topics = topicsServices.getTopicsByCourseId(courseId);
        return new ResponseEntity<>(topics, HttpStatus.OK);
    }

    // Delete a topic by topic ID
    @DeleteMapping("/{topicId}")
    public ResponseEntity<String> deleteTopic(@PathVariable Long topicId) {
        String response = topicsServices.deleteTopic(topicId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Delete all topics by course ID
    @DeleteMapping("/course/{courseId}")
    public ResponseEntity<String> deleteTopicsByCourseId(@PathVariable Long courseId) {
        String response = topicsServices.deleteTopicByCourseId(courseId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }



}
