package com.algo.algocourses.repository;

import com.algo.algocourses.entity.Courses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CoursesRepository extends JpaRepository<Courses, Long> {

}
