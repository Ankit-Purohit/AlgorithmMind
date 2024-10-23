package com.algo.algotopics.repository;

import com.algo.algotopics.entity.Topics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TopicsRepository extends JpaRepository<Topics,Long> {

    public List<Topics> findByCourseId(Long courseId);


}
