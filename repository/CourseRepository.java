package com.baitapjpa.baitapjpa.repository;

import com.baitapjpa.baitapjpa.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    @Query("SELECT c FROM Course c WHERE c.duration > :hours")
    List<Course> findByDurationGreaterThan(@Param("hours") int hours);

    @Query("SELECT COUNT(c) FROM Course c")
    long countCourses();
}
