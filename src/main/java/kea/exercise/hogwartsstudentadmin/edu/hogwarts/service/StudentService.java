package kea.exercise.hogwartsstudentadmin.edu.hogwarts.service;

import kea.exercise.hogwartsstudentadmin.edu.hogwarts.dto.StudentRequestDTO;
import kea.exercise.hogwartsstudentadmin.edu.hogwarts.dto.StudentResponseDTO;
import kea.exercise.hogwartsstudentadmin.edu.hogwarts.model.Student;

import java.util.List;

/**
 * This interface defines the contract for the StudentService.
 * It provides methods for CRUD operations on Student entities and for converting between Student and StudentDTO objects.
 *
 * @see kea.exercise.hogwartsstudentadmin.edu.hogwarts.service.StudentServiceImpl
 */
public interface StudentService {
    /**
     * Retrieves all students from the database.
     *
     * @return a list of all students, each represented as a StudentResponseDTO
     */
    List<StudentResponseDTO> findAllStudents();

    /**
     * Retrieves a student by their ID.
     *
     * @param id the ID of the student to retrieve
     * @return the student with the given ID, represented as a StudentResponseDTO
     */
    StudentResponseDTO findStudentById(Long id);

    /**
     * Saves a new student to the database.
     *
     * @param student the student to save, represented as a StudentRequestDTO
     * @return the saved student, represented as a StudentResponseDTO
     */
    StudentResponseDTO saveStudent(StudentRequestDTO student);

    /**
     * Updates an existing student in the database.
     *
     * @param student the updated student information, represented as a StudentRequestDTO
     * @param id the ID of the student to update
     * @return the updated student, represented as a StudentResponseDTO
     */
    StudentResponseDTO updateStudent(StudentRequestDTO student, Long id);

    /**
     * Partially updates an existing student in the database.
     * Only the fields provided in the StudentRequestDTO will be updated.
     *
     * @param student the updated student information, represented as a StudentRequestDTO
     * @param id the ID of the student to update
     * @return the updated student, represented as a StudentResponseDTO
     */
    StudentResponseDTO updateStudentPartially(StudentRequestDTO student, Long id);

    /**
     * Deletes a student from the database.
     *
     * @param id the ID of the student to delete
     * @return the deleted student, represented as a StudentResponseDTO
     */
    StudentResponseDTO deleteStudent(Long id);

    /**
     * Converts a Student entity to a StudentResponseDTO.
     *
     * @param student the student to convert
     * @return the student, represented as a StudentResponseDTO
     */
    StudentResponseDTO toDTO(Student student);

    /**
     * Converts a StudentRequestDTO to a Student entity.
     *
     * @param studentDTO the student DTO to convert
     * @return the student, represented as a Student entity
     */
    Student toEntity(StudentRequestDTO studentDTO);
}