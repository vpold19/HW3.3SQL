package com.skypro.SQl_HW33.repository;

import com.skypro.SQl_HW33.model.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FacultyRepository extends JpaRepository<Faculty,Long> {
    List<Faculty>findAllByColor(String color);
}
