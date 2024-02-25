package kea.exercise.hogwartsstudentadmin.edu.hogwarts.repository;

import kea.exercise.hogwartsstudentadmin.edu.hogwarts.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

/**
 * This interface extends JpaRepository and provides CRUD operations for the Teacher entity.
 * It is a part of the repository layer of the application, which is responsible for data access.
 * It also contains a custom query to find a teacher by their full name.
 *
 * @see org.springframework.data.jpa.repository.JpaRepository
 */
public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    /**
     * This method is a custom query that finds a teacher by their full name.
     * It handles cases where the teacher has no middle name and where they do have a middle name.
     *
     * @param fullName the full name of the teacher to find
     * @return an Optional containing the teacher if found, or an empty Optional if not found
     */
    @Query("SELECT t FROM Teacher t WHERE " +
            "(t.middleName IS NULL OR t.middleName = '') AND CONCAT(t.firstName, ' ', t.lastName) = :fullName " +
            "OR CONCAT(t.firstName, ' ', t.middleName, ' ', t.lastName) = :fullName")
    Optional<Teacher> findByFullName(@Param("fullName") String fullName);
}