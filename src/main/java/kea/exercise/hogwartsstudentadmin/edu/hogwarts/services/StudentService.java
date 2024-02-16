package kea.exercise.hogwartsstudentadmin.edu.hogwarts.services;

import kea.exercise.hogwartsstudentadmin.edu.hogwarts.models.Student;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface StudentService {
    List<Student> findAllStudents();
    Optional<Student> findStudentById(Long id);
    Student saveStudent(Student student);
    Student updateStudent(Student student, Student updatedStudent);
    void deleteStudent(Long id);
}

