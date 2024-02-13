package kea.exercise.hogwartsstudentadmin.edu.hogwarts.controllers;

import kea.exercise.hogwartsstudentadmin.edu.hogwarts.models.Student;
import kea.exercise.hogwartsstudentadmin.edu.hogwarts.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {
    @Autowired
    private StudentRepository studentRepository;

    @GetMapping("/students")
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        return studentRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/students")
    public Student createStudent(@RequestBody Student student) {
        return studentRepository.save(student);
    }

    @PutMapping("/students/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student student) {
        return studentRepository.findById(id) // find the student by id
                // using .map() on the Optional object returned by findById() to apply a function to the student if it is found
                .map(studentObj -> { // if the student is found, update the student
                    studentObj.setFirstName(student.getFirstName());
                    studentObj.setMiddleName(student.getMiddleName());
                    studentObj.setLastName(student.getLastName());
                    studentObj.setDateOfBirth(student.getDateOfBirth());
                    studentObj.setHouse(student.getHouse());
                    studentObj.setPrefect(student.isPrefect());
                    studentObj.setEnrollmentYear(student.getEnrollmentYear());
                    studentObj.setGraduationYear(student.getGraduationYear());
                    studentObj.setGraduated(student.isGraduated());

                    Student updatedStudent = studentRepository.save(studentObj); // save the updated student

                    return ResponseEntity.ok().body(updatedStudent); // return the updated student
                }).orElseGet(() -> ResponseEntity.notFound().build()); // if the student is not found, return a 404 Not Found response
    }

    @DeleteMapping("/students/{id}")
    public ResponseEntity<Student> deleteStudent(@PathVariable Long id) {
        if (studentRepository.existsById(id)) {
            studentRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
