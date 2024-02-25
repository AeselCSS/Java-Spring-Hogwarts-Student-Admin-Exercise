package kea.exercise.hogwartsstudentadmin.edu.hogwarts.repository;

import kea.exercise.hogwartsstudentadmin.edu.hogwarts.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

/**
 * TeacherRepository interface
 * This interface extends JpaRepository and is used to perform CRUD operations on the Teacher entity
 * It also contains a custom query to find a teacher by full name
 */
public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    /**
     * Custom query to find a teacher by full name
     * @param fullName
     * @return Optional of Teacher
     */
    @Query("SELECT t FROM Teacher t WHERE " +
            "(t.middleName IS NULL OR t.middleName = '') AND CONCAT(t.firstName, ' ', t.lastName) = :fullName " +
            "OR CONCAT(t.firstName, ' ', t.middleName, ' ', t.lastName) = :fullName")
    Optional<Teacher> findByFullName(@Param("fullName") String fullName);
}
