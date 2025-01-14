package com.baitapjpa.baitapjpa.repository;

import com.baitapjpa.baitapjpa.entity.Registration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegistrationRepository extends JpaRepository<Registration, Long> {
    List<Registration> findByStudentId(Long studentId);

    List<Registration> findByCourseId(Long courseId);
}

