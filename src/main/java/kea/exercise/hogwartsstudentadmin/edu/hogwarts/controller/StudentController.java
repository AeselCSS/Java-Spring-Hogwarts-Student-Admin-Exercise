package kea.exercise.hogwartsstudentadmin.edu.hogwarts.controller;

import jakarta.validation.Valid;
import kea.exercise.hogwartsstudentadmin.edu.hogwarts.model.Student;
import kea.exercise.hogwartsstudentadmin.edu.hogwarts.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/students")
    public List<Student> getAllStudents() {
        return studentService.findAllStudents();
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        return studentService.findStudentById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/students")
    public ResponseEntity<Student> createStudent(@Valid @RequestBody Student student) {
        Student savedStudent = studentService.saveStudent(student);
        return ResponseEntity.ok(savedStudent);
    }

    @PutMapping("/students/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @Valid @RequestBody Student student) {
        return studentService.findStudentById(id)
                .map(studentObj -> ResponseEntity.ok().body(studentService.updateStudent(studentObj, student)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PatchMapping("/students/{id}")
    public ResponseEntity<Student> updateStudentPartially(@PathVariable Long id, @RequestBody Student student) {
        return studentService.findStudentById(id)
                .map(studentObj -> ResponseEntity.ok().body(studentService.updateStudentPartially(studentObj, student)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/students/{id}")
    public ResponseEntity<Student> deleteStudent(@PathVariable Long id) {
        return studentService.findStudentById(id)
                .map(student -> {
                    studentService.deleteStudent(id);
                    return ResponseEntity.ok().<Student>build();
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }


}

