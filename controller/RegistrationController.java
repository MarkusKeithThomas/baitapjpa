package com.baitapjpa.baitapjpa.controller;

import com.baitapjpa.baitapjpa.entity.Course;
import com.baitapjpa.baitapjpa.entity.Registration;
import com.baitapjpa.baitapjpa.entity.Student;
import com.baitapjpa.baitapjpa.repository.CourseRepository;
import com.baitapjpa.baitapjpa.repository.RegistrationRepository;
import com.baitapjpa.baitapjpa.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/aoi/students/{studentId}/courses")
public class RegistrationController {
    @Autowired
    private RegistrationRepository registrationRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private CourseRepository courseRepository;

    @PostMapping
    public ResponseEntity<List<Registration>> registerCoursesForStudent(@PathVariable Long studentId, @RequestBody List<Long> courseIds) {
        Optional<Student> studentOpt = studentRepository.findById(studentId);
        if (studentOpt.isEmpty()) return ResponseEntity.notFound().build();

        Student student = studentOpt.get();
        List<Registration> registrations = courseIds.stream()
                .map(courseId -> {
                    Course course = courseRepository.findById(courseId).orElseThrow();
                    return new Registration(student, course, LocalDate.now());
                })
                .collect(Collectors.toList());
        registrationRepository.saveAll(registrations);
        return ResponseEntity.ok(registrations);
    }

    @GetMapping
    public ResponseEntity<List<Course>> getCoursesForStudent(@PathVariable Long studentId) {
        List<Course> courses = registrationRepository.findByStudentId(studentId).stream()
                .map(Registration::getCourse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(courses);
    }
}

