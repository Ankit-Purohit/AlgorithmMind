package com.algo.algotopics.service;

import com.algo.algotopics.dto.TopicDto;
import com.algo.algotopics.entity.Topics;
import com.algo.algotopics.repository.TopicsRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class TopicsServicesImpl implements TopicServices {


    @Autowired
    private TopicsRepository topicsRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public TopicDto addTopic(TopicDto topicDto) {
        Topics topics=modelMapper.map(topicDto,Topics.class);
        Topics savedTopic=topicsRepository.save(topics);
        return modelMapper.map(savedTopic,TopicDto.class);
    }

    // Get a specific topic by ID
    @Override
    public TopicDto getTopic(Long topicId) {
        Optional<Topics> optionalTopic = topicsRepository.findById(topicId);
        if (optionalTopic.isPresent()) {
            return modelMapper.map(optionalTopic.get(), TopicDto.class);
        }
        throw new RuntimeException("Topic not found with ID: " + topicId);
    }

    // Get all topics by Course ID
    @Override
    public List<TopicDto> getTopicsByCourseId(Long courseId) {
        List<Topics> topics = topicsRepository.findByCourseId(courseId);
        return topics.stream()
                .map(topic -> modelMapper.map(topic, TopicDto.class))
                .collect(Collectors.toList());
    }

    // Delete a topic by Topic ID
    @Override
    public String deleteTopic(Long topicId) {
        Optional<Topics> optionalTopic = topicsRepository.findById(topicId);
        if (optionalTopic.isPresent()) {
            topicsRepository.deleteById(topicId);
            return "Topic deleted successfully.";
        }
        return "Topic not found with ID: " + topicId;
    }

    // Delete all topics by Course ID
    @Override
    public String deleteTopicByCourseId(Long courseId) {
        List<Topics> topics = topicsRepository.findByCourseId(courseId);
        if (!topics.isEmpty()) {
            topicsRepository.deleteAll(topics);
            return "All topics for course ID " + courseId + " deleted successfully.";
        }
        return "No topics found for course ID: " + courseId;
    }
}
