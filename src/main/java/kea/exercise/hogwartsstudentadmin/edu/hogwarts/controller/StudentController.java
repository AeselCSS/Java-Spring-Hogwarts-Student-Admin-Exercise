package kea.exercise.hogwartsstudentadmin.edu.hogwarts.controller;

import jakarta.validation.Valid;
import kea.exercise.hogwartsstudentadmin.edu.hogwarts.dto.StudentDTO;
import kea.exercise.hogwartsstudentadmin.edu.hogwarts.model.Student;
import kea.exercise.hogwartsstudentadmin.edu.hogwarts.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/students")
    public List<StudentDTO> getAllStudents() {
        return studentService.findAllStudents().stream()
                .map(studentService::convertToDTO)
                .toList();
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<StudentDTO> getStudentById(@PathVariable Long id) {
        return studentService.findStudentById(id)
                .map(studentService::convertToDTO)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/students")
    public ResponseEntity<StudentDTO> createStudent(@Valid @RequestBody StudentDTO studentDTO) {
        Student savedStudent = studentService.saveStudent(studentService.convertFromDTO(studentDTO));
        return ResponseEntity.ok(studentService.convertToDTO(savedStudent));
    }

    @PutMapping("/students/{id}")
    public ResponseEntity<StudentDTO> updateStudent(@PathVariable Long id, @Valid @RequestBody StudentDTO studentDTO) {
        return studentService.findStudentById(id)
                .map(studentObj -> {
                    Student updatedStudent = studentService.updateStudent(studentObj, studentService.convertFromDTO(studentDTO));
                    return ResponseEntity.ok().body(studentService.convertToDTO(updatedStudent));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PatchMapping("/students/{id}")
    public ResponseEntity<StudentDTO> updateStudentPartially(@PathVariable Long id, @RequestBody StudentDTO studentDTO) {
        return studentService.findStudentById(id)
                .map(studentObj -> {
                    Student updatedStudent = studentService.updateStudentPartially(studentObj, studentService.convertFromDTO(studentDTO));
                    return ResponseEntity.ok().body(studentService.convertToDTO(updatedStudent));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/students/{id}")
    public ResponseEntity<StudentDTO> deleteStudent(@PathVariable Long id) {
        return studentService.findStudentById(id)
                .map(student -> {
                    studentService.deleteStudent(id);
                    return ResponseEntity.ok().<StudentDTO>build();
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }
}