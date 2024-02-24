package kea.exercise.hogwartsstudentadmin.edu.hogwarts.repository;

import kea.exercise.hogwartsstudentadmin.edu.hogwarts.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    @Query("SELECT t FROM Teacher t WHERE " +
            "(t.middleName IS NULL OR t.middleName = '') AND CONCAT(t.firstName, ' ', t.lastName) = :fullName " +
            "OR CONCAT(t.firstName, ' ', t.middleName, ' ', t.lastName) = :fullName")
    Optional<Teacher> findByFullName(@Param("fullName") String fullName);
}
