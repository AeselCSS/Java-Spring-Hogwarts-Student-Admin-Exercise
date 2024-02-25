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
    List<StudentResponseDTO> findAllStudents();
    StudentResponseDTO findStudentById(Long id);
    StudentResponseDTO saveStudent(StudentRequestDTO student);
    StudentResponseDTO updateStudent(StudentRequestDTO student, Long id);
    StudentResponseDTO updateStudentPartially(StudentRequestDTO student, Long id);
    StudentResponseDTO deleteStudent(Long id);
    StudentResponseDTO toDTO(Student student);
    Student toEntity(StudentRequestDTO studentDTO);
}


