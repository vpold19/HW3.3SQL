package com.skypro.SQl_HW33.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.skypro.SQl_HW33.model.Faculty;
import com.skypro.SQl_HW33.model.Student;
import com.skypro.SQl_HW33.repository.FacultyRepository;
import com.skypro.SQl_HW33.repository.StudentRepository;
import com.skypro.SQl_HW33.service.StudentService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatcher;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(StudentController.class)
public class StudentControllerMockTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    StudentRepository studentRepository;

    @MockBean
    FacultyRepository facultyRepository;

    @SpyBean
    StudentService studentService;

    @Test
    void getById() throws Exception {
        Student student = new Student(1L, "ivan", 20);
        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));
        mockMvc.perform(MockMvcRequestBuilders.get("/student/1")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("ivan"))
                .andExpect(jsonPath("$.age").value("20"));
    }

    @Test
    void create() throws Exception {
        Student student = new Student(1L, "ivan", 20);
        when(studentRepository.save(ArgumentMatchers.any(Student.class)))
                .thenReturn(student);

        mockMvc.perform(MockMvcRequestBuilders.post("/student")
                        .content(objectMapper.writeValueAsString(student))
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("ivan"))
                .andExpect(jsonPath("$.age").value("20"));
    }

    @Test
    void update() throws Exception {
        Student student = new Student(1L, "ivan", 20);
        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));
        when(studentRepository.save(ArgumentMatchers.any(Student.class)))
                .thenReturn(student);

        mockMvc.perform(MockMvcRequestBuilders.put("/student/1")
                        .content(objectMapper.writeValueAsString(student))
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("ivan"))
                .andExpect(jsonPath("$.age").value("20"));
    }

    @Test
    void delete() throws Exception {
        Student student = new Student(1L, "ivan", 20);
        long id = 1L;
        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));

        mockMvc.perform(MockMvcRequestBuilders.delete("/student/" + id)
                        .content(objectMapper.writeValueAsString(student))
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());


//                .andExpect(jsonPath("$.name").value("ivan"))
//                .andExpect(jsonPath("$.age").value("20"));
    }
    @Test
    void filteredByAge() throws Exception {
        Student student = new Student(1L, "ivan", 20);
        when(studentRepository.findAllByAgeBetween(10,20))
                .thenReturn(Arrays.asList(
                        new Student(1L, "ivan", 20),
                        new Student(2L, "Genadiy", 15)
                ));
        mockMvc.perform(MockMvcRequestBuilders.get("/student/by-age?min=10&max=20")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].name").value("ivan"))
                .andExpect(jsonPath("$[1].name").value("Genadiy"));

    }
    @Test
    void findByFaculty() throws Exception{
        List<Student> students =Arrays.asList(
                new Student(1L, "ivan", 20),
                new Student(2L, "Genadiy", 15));
        Faculty faculty = new Faculty(1l, "math", "red");
        faculty.setStudent(students);

        when(facultyRepository.findById(1L)).thenReturn(Optional.of(faculty));


        mockMvc.perform(MockMvcRequestBuilders.get("/student/by-faculty?facultyId=1")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
//                .andExpect(jsonPath("$[1].name").value("ivan"))
//                .andExpect(jsonPath("$[2].name").value("Genadiy"));
    }
}
