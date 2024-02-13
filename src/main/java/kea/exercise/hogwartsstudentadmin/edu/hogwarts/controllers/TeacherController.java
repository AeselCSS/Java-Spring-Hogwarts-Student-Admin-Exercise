package kea.exercise.hogwartsstudentadmin.edu.hogwarts.controllers;

import kea.exercise.hogwartsstudentadmin.edu.hogwarts.models.Teacher;
import kea.exercise.hogwartsstudentadmin.edu.hogwarts.repositories.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TeacherController {
    @Autowired
    private TeacherRepository teacherRepository;

    @GetMapping("/teachers")
    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }

    @GetMapping("/teachers/{id}")
    public ResponseEntity<Teacher> getTeacherById(@PathVariable Long id) {
        return teacherRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/teachers")
    public Teacher createTeacher(@RequestBody Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    @PutMapping("/teachers/{id}")
    public ResponseEntity<Teacher> updateTeacher(@PathVariable Long id, @RequestBody Teacher teacher) {
        return teacherRepository.findById(id)
                .map(teacherObj -> {
                    teacherObj.setFirstName(teacher.getFirstName());
                    teacherObj.setMiddleName(teacher.getMiddleName());
                    teacherObj.setLastName(teacher.getLastName());
                    teacherObj.setDateOfBirth(teacher.getDateOfBirth());
                    teacherObj.setHouse(teacher.getHouse());
                    teacherObj.setHeadOfHouse(teacher.isHeadOfHouse());
                    teacherObj.setEmployment(teacher.getEmployment());
                    teacherObj.setEmploymentStart(teacher.getEmploymentStart());
                    teacherObj.setEmploymentEnd(teacher.getEmploymentEnd());
                    Teacher updatedTeacher = teacherRepository.save(teacherObj);
                    return ResponseEntity.ok().body(updatedTeacher);
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/teachers/{id}")
    public ResponseEntity<Teacher> deleteTeacher(@PathVariable Long id) {
        if (teacherRepository.existsById(id)) {
            teacherRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
