package kea.exercise.hogwartsstudentadmin.edu.hogwarts.service;

import kea.exercise.hogwartsstudentadmin.edu.hogwarts.dto.StudentRequestDTO;
import kea.exercise.hogwartsstudentadmin.edu.hogwarts.dto.StudentResponseDTO;
import kea.exercise.hogwartsstudentadmin.edu.hogwarts.model.Student;

import java.util.List;

/**
 * Interface for the StudentService class
 * @see kea.exercise.hogwartsstudentadmin.edu.hogwarts.service.StudentServiceImpl
 */
public interface StudentService {
    /**
     * Method to find all students
     * @return a list of all students
     */
    List<StudentResponseDTO> findAllStudents();

    /**
     * Method to find a student by id
     * @param id
     * @return a student
     */
    StudentResponseDTO findStudentById(Long id);

    /**
     * Method to save a student
     * @param student
     * @return a student
     */
    StudentResponseDTO saveStudent(StudentRequestDTO student);

    /**
     * Method to update a student
     * @param student
     * @param id
     * @return a student
     */
    StudentResponseDTO updateStudent(StudentRequestDTO student, Long id);

    /**
     * Method to update a student partially
     * @param student
     * @param id
     * @return a student
     */
    StudentResponseDTO updateStudentPartially(StudentRequestDTO student, Long id);

    /**
     * Method to delete a student
     * @param id
     * @return a student
     */
    StudentResponseDTO deleteStudent(Long id);

    /**
     * Method to convert a student to a DTO
     * @param student
     * @return a studentResponseDTO
     */
    StudentResponseDTO toDTO(Student student);

    /**
     * Method to convert a studentRequestDTO to an entity
     * @param studentDTO
     * @return a student
     */
    Student toEntity(StudentRequestDTO studentDTO);
}


