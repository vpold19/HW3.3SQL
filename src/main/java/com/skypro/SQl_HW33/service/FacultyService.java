package com.skypro.SQl_HW33.service;

import com.skypro.SQl_HW33.exception.DataNotFoundedException;
import com.skypro.SQl_HW33.exception.FacultyNotFoundException;
import com.skypro.SQl_HW33.model.Faculty;
import com.skypro.SQl_HW33.repository.FacultyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class FacultyService {
    private static final Logger logger = LoggerFactory.getLogger(FacultyService.class);
    private final FacultyRepository facultyRepository;

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Faculty getById(Long id) {
        logger.info("invoked method getById");
        logger.debug("id = " + id);
        return facultyRepository.findById(id).orElseThrow(DataNotFoundedException::new);
    }

    public Collection<Faculty> getAll() {
        logger.info("invoked method getAll");
        return facultyRepository.findAll();
    }

    public Faculty create(Faculty faculty) {
        logger.info("invoked method create");
        facultyRepository.save(faculty);
        return faculty;
    }

    public Faculty update(Long id, Faculty faculty) {
        logger.info("invoked method update");
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
        logger.info("invoked method delete");
        Faculty faculty = facultyRepository.findById(id)
                .orElseThrow(DataNotFoundedException::new);
        facultyRepository.delete(faculty);
        return faculty;
    }

    public Collection<Faculty> getByColor(String color) {
        logger.info("invoked method getByColor");
        return facultyRepository.findAllByColor(color);

    }

    public Collection<Faculty> getByColorOrName(String color, String name) {
        logger.info("invoked method getByColorOrName");
        return facultyRepository.findAllByColorIgnoreCaseOrNameIgnoreCase(color, name);
    }

    public Faculty getByStudentId(Long studentId) {
        logger.info("invoked method getByStudentId");
        return facultyRepository.findByStudentId(studentId).orElseThrow(FacultyNotFoundException::new);
    }
}

