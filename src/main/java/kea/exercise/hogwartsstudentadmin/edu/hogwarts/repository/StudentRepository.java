package kea.exercise.hogwartsstudentadmin.edu.hogwarts.repository;

import kea.exercise.hogwartsstudentadmin.edu.hogwarts.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

/**
 * This interface extends JpaRepository and provides CRUD operations for the Student entity.
 * It is a part of the repository layer of the application, which is responsible for data access.
 * It also contains a custom query to find a student by their full name.
 *
 * @see org.springframework.data.jpa.repository.JpaRepository
 */
public interface StudentRepository extends JpaRepository<Student, Long> {

    /**
     * This method is a custom query that finds a student by their full name.
     * It handles cases where the student has no middle name and where they do have a middle name.
     *
     * @param fullName the full name of the student to find
     * @return an Optional containing the student if found, or an empty Optional if not found
     */
    @Query("SELECT s FROM Student s WHERE " +
            "(s.middleName IS NULL OR s.middleName = '') AND CONCAT(s.firstName, ' ', s.lastName) = :fullName " +
            "OR CONCAT(s.firstName, ' ', s.middleName, ' ', s.lastName) = :fullName")
    Optional<Student> findByFullName(@Param("fullName") String fullName);
}