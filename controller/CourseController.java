package com.baitapjpa.baitapjpa.controller;

import com.baitapjpa.baitapjpa.entity.Course;
import com.baitapjpa.baitapjpa.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/aoi/courses")
public class CourseController {
    @Autowired
    private CourseRepository courseRepository;

    // GET: Lấy danh sách các khóa học với thời lượng lớn hơn hours
    @GetMapping
    public ResponseEntity<List<Course>> getCoursesWithDurationGreaterThan(@RequestParam int durationGreaterThan) {
        List<Course> courses = courseRepository.findByDurationGreaterThan(durationGreaterThan);
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }

    // GET: Đếm tổng số khóa học
    @GetMapping("/count")
    public ResponseEntity<Long> getCourseCount() {
        long count = courseRepository.countCourses();
        return new ResponseEntity<>(count, HttpStatus.OK);
    }
}

