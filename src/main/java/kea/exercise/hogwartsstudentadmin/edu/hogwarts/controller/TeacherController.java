package kea.exercise.hogwartsstudentadmin.edu.hogwarts.controller;

import kea.exercise.hogwartsstudentadmin.edu.hogwarts.dto.TeacherRequestDTO;
import kea.exercise.hogwartsstudentadmin.edu.hogwarts.dto.TeacherResponseDTO;
import kea.exercise.hogwartsstudentadmin.edu.hogwarts.service.TeacherService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TeacherController {
    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping("/teachers")
    public List<TeacherResponseDTO> getAllTeachers() {
        return teacherService.findAllTeachers();
    }

    @GetMapping("/teachers/{id}")
    public ResponseEntity<TeacherResponseDTO> getTeacherById(@PathVariable Long id) {
        return ResponseEntity.ok(teacherService.findTeacherById(id));
    }

    @PostMapping("/teachers")
    public ResponseEntity<TeacherResponseDTO> createTeacher(@RequestBody TeacherRequestDTO teacher) {
        return ResponseEntity.ok(teacherService.saveTeacher(teacher));
    }

    @PutMapping("/teachers/{id}")
    public ResponseEntity<TeacherResponseDTO> updateTeacher(@RequestBody TeacherRequestDTO teacher, @PathVariable Long id) {
        return ResponseEntity.ok(teacherService.updateTeacher(teacher, id));
    }

    @PatchMapping("/teachers/{id}")
    public ResponseEntity<TeacherResponseDTO> updateTeacherPartially(@RequestBody TeacherRequestDTO teacher, @PathVariable Long id) {
        return ResponseEntity.ok(teacherService.updateTeacherPartially(teacher, id));
    }

    @DeleteMapping("/teachers/{id}")
    public ResponseEntity<TeacherResponseDTO> deleteTeacher(@PathVariable Long id) {
        return ResponseEntity.ok(teacherService.deleteTeacher(id));
    }
}
