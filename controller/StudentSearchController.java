package com.baitapjpa.baitapjpa.controller;

import com.baitapjpa.baitapjpa.entity.Student;
import com.baitapjpa.baitapjpa.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/aoi/students/search")
public class StudentSearchController {
    @Autowired
    private StudentRepository studentRepository;

    // GET: Tìm kiếm sinh viên theo tên
    @GetMapping
    public ResponseEntity<List<Student>> searchStudents(@RequestParam String name) {
        List<Student> students = studentRepository.findByNameContaining(name);
        return new ResponseEntity<>(students, HttpStatus.OK);
    }
}
