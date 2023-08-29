package com.skypro.SQl_HW33.repository;

import com.skypro.SQl_HW33.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {
    List<Student> findAllByAge(int age);

    List<Student>findAllByAgeBetween(int min,int max);

    List<Student> findAllByFacultyId(Long facultyId);
    }

