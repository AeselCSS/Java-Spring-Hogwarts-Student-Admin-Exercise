package kea.exercise.hogwartsstudentadmin.edu.hogwarts.service;

import kea.exercise.hogwartsstudentadmin.edu.hogwarts.model.Student;

import java.util.List;
import java.util.Optional;

public interface StudentService {
    List<Student> findAllStudents();
    Optional<Student> findStudentById(Long id);
    Student saveStudent(Student student);
    Student updateStudent(Student student, Student updatedStudent);
    Student updateStudentPartially(Student student, Student updatedStudent);
    void deleteStudent(Long id);
}

