package com.skypro.SQl_HW33.service;

import com.skypro.SQl_HW33.exception.DataNotFoundedException;
import com.skypro.SQl_HW33.model.Faculty;
import com.skypro.SQl_HW33.repository.FacultyRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class FacultyService {
    private final FacultyRepository facultyRepository;

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Faculty getById(Long id) {
        return facultyRepository.findById(id).orElseThrow(DataNotFoundedException::new);
    }

    public Collection<Faculty> getAll() {
        return facultyRepository.findAll();
    }

    public Faculty create(Faculty faculty) {
        facultyRepository.save(faculty);
        return faculty;
    }

    public Faculty update(Long id, Faculty faculty) {
        if (facultyRepository.existsById(id)) {
            facultyRepository.findById(id)
                    .ifPresent(f -> {
                        f.setName(faculty.getName());
                        f.setColor(faculty.getColor());
                        facultyRepository.save(f);
                    });
            return faculty;
        }
        throw new DataNotFoundedException();
    }


    public Faculty delete(Long id) {
        Faculty faculty = facultyRepository.findById(id)
                .orElseThrow(DataNotFoundedException::new);
         facultyRepository.delete(faculty);
         return faculty;
        }

    public Collection<Faculty> getByColor(String color) {
        return facultyRepository.findAllByColor(color);
    }
}

