package com.skypro.SQl_HW33.controller;

import com.skypro.SQl_HW33.Application;
import com.skypro.SQl_HW33.model.Faculty;
import com.skypro.SQl_HW33.model.Student;
import com.skypro.SQl_HW33.repository.FacultyRepository;
import com.skypro.SQl_HW33.repository.StudentRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(classes = Application.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class StudentControllerTest {
    @Autowired
    TestRestTemplate template;
    @Autowired
    FacultyRepository facultyRepository;
    @Autowired
    StudentRepository studentRepository;
    @AfterEach
    void clearDb() {
        studentRepository.deleteAll();
        facultyRepository.deleteAll();
    }
private final
    @Test
    void getAll() {
    }

    @Test
    void getById() {
    }

    @Test
    void filteredByAge() {
    }

    @Test
    void create()throws Exception {
//        String name = "math";
//        Integer age = 15;
//        Student response = studentRepository.save(new Student(name,age));
//
//        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
//        assertThat(response.getBody()).isNotNull();
//        assertThat(response.getBody().getName()).isEqualTo(name);
//        assertThat(response.getBody().getColor()).isEqualTo(color);
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    @Test
    void filteredByAgeMinMax() {
    }

    @Test
    void findByFaculty() {
    }

    @Test
    void getAmountOfStudents() {
    }

    @Test
    void getAverageAgeOfStudents() {
    }

    @Test
    void getLastNumbersOfStudents() {
    }
}