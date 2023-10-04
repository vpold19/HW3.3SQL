package com.skypro.SQl_HW33.service;

import com.skypro.SQl_HW33.exception.DataNotFoundedException;
import com.skypro.SQl_HW33.exception.StudentNotFoundException;
import com.skypro.SQl_HW33.model.Student;
import com.skypro.SQl_HW33.repository.FacultyRepository;
import com.skypro.SQl_HW33.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service

public class StudentService {
    private static final Logger logger = LoggerFactory.getLogger(StudentService.class);
    private final StudentRepository studentRepository;
    private final FacultyRepository facultyRepository;

    public StudentService(StudentRepository studentRepository,
                          FacultyRepository facultyRepository) {
        this.studentRepository = studentRepository;
        this.facultyRepository = facultyRepository;
    }

    public Collection<Student> getByAge(int age) {
        logger.info("invoked method getByAge");
        return studentRepository.findAllByAge(age);
    }

    public Student getById(Long id) {
        logger.info("invoked method getById");
        return studentRepository.findById(id).orElseThrow(DataNotFoundedException::new);
    }

    public Collection<Student> getAll() {
        logger.info("invoked method getAll");
        return studentRepository.findAll();
    }

    public Student create(Student student) {
        logger.info("invoked method create");
        return studentRepository.save(student);
    }

    public Student update(Long id, Student student) {
        logger.info("invoked method update");
        Student existingStudent = studentRepository.findById(id).orElseThrow(DataNotFoundedException::new);
        Optional.ofNullable(student.getName()).ifPresent(existingStudent::setName);
//        String name = student.getName();
//        existingStudent.setName(name);
        Optional.of(student.getAge()).ifPresent(existingStudent::setAge);
        return studentRepository.save(existingStudent);
    }

    public void delete(Long id) {
        logger.info("invoked method delete");
        Student existingStudent = studentRepository.findById(id).orElseThrow(DataNotFoundedException::new);
        studentRepository.delete(existingStudent);
    }

    public Collection<Student> getByAgeBetween(int min, int max) {
        logger.info("invoked method getByAgeBetween");
        return studentRepository.findAllByAgeBetween(min, max);
    }

    public Collection<Student> getByFacultyId(Long facultyId) {
        logger.info("invoked method getByFacultyId");
        return studentRepository.findAllByFacultyId(facultyId);
    }

    public Long count() {
        logger.info("invoked method getAmountOfStudents");
        return studentRepository.countStudent();
    }

    public Float getAverageAgeOfStudents() {
        logger.info("invoked method getAverageAgeOfStudents");
        return studentRepository.getAverageAgeOfStudents();
    }

    public List<Student> getLastNumbersOfStudent() {
        logger.info("invoked method getLastNumbersOfStudent");
        //PageRequest pageRequest = PageRequest.of()
        return studentRepository.getLastNumberOfStudents();
    }

    public void printAsync() {
        List<Student> all = studentRepository.findAll();
        System.out.println(all.get(0));
        System.out.println(all.get(1));

        Thread t1 = new Thread(() -> {
            System.out.println(all.get(3));
            System.out.println(all.get(4));
        });
        Thread t2 = new Thread(() -> {
            System.out.println(all.get(5));
            System.out.println(all.get(6));
        });
        t1.start();
        t2.start();
    }

    public void printSync() {
        List<Student> all = studentRepository.findAll();

        printSync(all.get(0));
        printSync(all.get(1));

        Thread t1 = new Thread(() -> {
            printSync(all.get(3));
            printSync(all.get(4));
        });
        Thread t2 = new Thread(() -> {
            printSync(all.get(5));
            printSync(all.get(6));
        });
        t1.start();
        t2.start();
    }

    private synchronized void printSync(Student student) {
        System.out.println(student);
    }

    public List<String> getAllStartsWithA() {
        return studentRepository.findAll().stream()
                .map(Student::getName)
                .filter(s -> s.startsWith("A"))
                .sorted()
                .collect(Collectors.toList());
    }
    public double getAverageAge(){
        return  studentRepository.findAll().stream()
                .mapToInt(Student::getAge)
                .average()
                .orElseThrow(StudentNotFoundException:: new);

    }

}
