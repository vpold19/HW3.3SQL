package com.skypro.SQl_HW33.service;

import com.skypro.SQl_HW33.exception.DataNotFoundedException;
import com.skypro.SQl_HW33.model.Student;
import com.skypro.SQl_HW33.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service

public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Collection<Student> getByAge(int age){
        return studentRepository.findAllByAge(age);
    }

    public Student getById(Long id){
        return studentRepository.findById(id).orElseThrow(DataNotFoundedException::new);
    }
    public Collection<Student> getAll(){
        return studentRepository.findAll();
    }
    public Student create(Student student){
        return studentRepository.save(student);
    }
    public Student update(Long id, Student student){
        Student existingStudent = studentRepository.findById(id).orElseThrow(DataNotFoundedException::new);
        Optional.ofNullable(student.getName()).ifPresent(existingStudent::setName);
        Optional.ofNullable(student.getAge()).ifPresent(existingStudent::setAge);
        return studentRepository.save(student);
    }
    public Student delete(Long id){
        Student existingStudent = studentRepository.findById(id).orElseThrow(DataNotFoundedException::new);
        studentRepository.delete(existingStudent);
        return existingStudent;
    }
}
