package kea.exercise.hogwartsstudentadmin.edu.hogwarts.controller;

import kea.exercise.hogwartsstudentadmin.edu.hogwarts.dto.StudentRequestDTO;
import kea.exercise.hogwartsstudentadmin.edu.hogwarts.dto.StudentResponseDTO;
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
    public List<StudentResponseDTO> getAllStudents() {
        return studentService.findAllStudents();
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<StudentResponseDTO> getStudentById(@PathVariable Long id) {
        return ResponseEntity.ok(studentService.findStudentById(id));
    }

    @PostMapping("/students")
    public ResponseEntity<StudentResponseDTO> createStudent(@RequestBody StudentRequestDTO studentDTO) {
        return ResponseEntity.ok(studentService.saveStudent(studentDTO));
    }

    @PutMapping("/students/{id}")
    public ResponseEntity<StudentResponseDTO> updateStudent(@PathVariable Long id, @RequestBody StudentRequestDTO studentDTO) {
        return ResponseEntity.ok(studentService.updateStudent(studentDTO, id));
    }

    @PatchMapping("/students/{id}")
    public ResponseEntity<StudentResponseDTO> updateStudentPartially(@PathVariable Long id, @RequestBody StudentRequestDTO studentDTO) {
        return ResponseEntity.ok(studentService.updateStudentPartially(studentDTO, id));
    }

    @DeleteMapping("/students/{id}")
    public ResponseEntity<StudentResponseDTO> deleteStudent(@PathVariable Long id) {
        return ResponseEntity.ok(studentService.deleteStudent(id));
    }
}