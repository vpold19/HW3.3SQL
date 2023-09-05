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

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(classes = Application.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FacultyControllerTest {
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

    @Test
    void create() throws Exception {
        String name = "math";
        String color = "red";
        ResponseEntity<Faculty> response = createFaculty(name, color);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getName()).isEqualTo(name);
        assertThat(response.getBody().getColor()).isEqualTo(color);
    }

    @Test
    void getById() {
        ResponseEntity<Faculty> response = createFaculty("math", "red");
        Long facultyId = response.getBody().getId();


        response = template.getForEntity("/faculty/" + facultyId, Faculty.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getName()).isEqualTo("math");
        assertThat(response.getBody().getColor()).isEqualTo("red");
    }

    @Test
    void update() {
        ResponseEntity<Faculty> response = createFaculty("math", "red");
        Long facultyId = response.getBody().getId();


        template.put("/faculty/" + facultyId, new Faculty(null, "math", "blue"));
        response = template.getForEntity("/faculty/" + facultyId, Faculty.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getColor()).isEqualTo("blue");
    }

    @Test
    void delete() {
        ResponseEntity<Faculty> response = createFaculty("math", "red");
        Long facultyId = response.getBody().getId();


        template.delete("/faculty/" + facultyId);
        response = template.getForEntity("/faculty/" + facultyId, Faculty.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Test
    void getAll() {
        createFaculty("english", "yellow");
        createFaculty("philosophy", "blue");


        ResponseEntity<Collection> response = template
                .getForEntity("/faculty", Collection.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().size()).isEqualTo(2);
    }
    @Test
    void filteredByColorOrName() {
        String color = "red";
        createFaculty("math", color);
        createFaculty("philosophy", "blue");


        ResponseEntity<Collection> response = template
                .getForEntity("/faculty/by-color-or-name?colorOrName=" + color, Collection.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().size()).isEqualTo(1);
        Map<String,String> next =(HashMap)response.getBody().iterator().next();
        assertThat(next.get("color")).isEqualTo(color);
    }
    @Test
    void filtered() {
        String color = "red";
        createFaculty("math", color);
        createFaculty("philosophy", "blue");


        ResponseEntity<Collection> response = template
                .getForEntity("/faculty/filtered?color=" + color, Collection.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().size()).isEqualTo(1);
        Map<String,String> next =(HashMap)response.getBody().iterator().next();
        assertThat(next.get("color")).isEqualTo(color);
    }
    @Test
    void byStudent(){
        ResponseEntity<Faculty> response = createFaculty("math", "red");
        Faculty faculty = response.getBody();
        Student student = new Student(null, "Ivan", 20);
        student.setFaculty(faculty);
        ResponseEntity<Student> studentResponse = template.postForEntity("/student", student, Student.class);
        assertThat(studentResponse.getStatusCode()).isEqualTo(HttpStatus.OK);

        Long studentId = studentResponse.getBody().getId();

        response =
                template.getForEntity("/faculty/by-student?studentId=" + studentId,Faculty.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody()).isEqualTo(faculty);
    }

    private ResponseEntity<Faculty> createFaculty(String name, String color) {
        ResponseEntity<Faculty> response = template.postForEntity("/faculty",
                new Faculty(null, name, color),
                Faculty.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        return response;

    }
}
