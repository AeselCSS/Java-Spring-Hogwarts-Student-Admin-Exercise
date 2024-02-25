package kea.exercise.hogwartsstudentadmin.edu.hogwarts.repository;

import kea.exercise.hogwartsstudentadmin.edu.hogwarts.model.Student;
import kea.exercise.hogwartsstudentadmin.edu.hogwarts.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

/**
 * StudentRepository interface
 * This interface extends JpaRepository and is used to perform CRUD operations on the Student entity
 * It also contains a custom query to find a student by full name
 */
public interface StudentRepository extends JpaRepository<Student, Long> {

    /**
     * Custom query to find a student by full name
     * @param fullName
     * @return Optional of Student
     */
    @Query("SELECT s FROM Student s WHERE " +
            "(s.middleName IS NULL OR s.middleName = '') AND CONCAT(s.firstName, ' ', s.lastName) = :fullName " +
            "OR CONCAT(s.firstName, ' ', s.middleName, ' ', s.lastName) = :fullName")
    Optional<Student> findByFullName(@Param("fullName") String fullName);
}
