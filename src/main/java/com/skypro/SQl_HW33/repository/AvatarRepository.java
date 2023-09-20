package com.skypro.SQl_HW33.repository;

import com.skypro.SQl_HW33.model.Avatar;
import com.skypro.SQl_HW33.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AvatarRepository extends JpaRepository<Avatar,Long> {
    Optional<Avatar> findByStudent(Student student);

}
