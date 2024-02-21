package kea.exercise.hogwartsstudentadmin.edu.hogwarts.controller;

import jakarta.validation.Valid;
import kea.exercise.hogwartsstudentadmin.edu.hogwarts.dto.TeacherDTO;
import kea.exercise.hogwartsstudentadmin.edu.hogwarts.model.Teacher;
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
    public List<TeacherDTO> getAllTeachers() {
        return teacherService.findAllTeachers().stream()
                .map(teacherService::convertToDTO)
                .toList();
    }

    @GetMapping("/teachers/{id}")
    public ResponseEntity<TeacherDTO> getTeacherById(@PathVariable Long id) {
        return teacherService.findTeacherById(id)
                .map(teacherService::convertToDTO)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/teachers")
    public ResponseEntity<Teacher> createTeacher(@RequestBody Teacher teacher) {
        Teacher savedTeacher = teacherService.saveTeacher(teacher);
        return ResponseEntity.ok(savedTeacher);
    }

    @PutMapping("/teachers/{id}")
    public ResponseEntity<TeacherDTO> updateTeacher(@PathVariable Long id, @Valid @RequestBody Teacher teacher) {
        return teacherService.findTeacherById(id)
                .map(teacherObj -> {
                    Teacher updatedTeacher = teacherService.updateTeacher(teacherObj, teacher);
                    return ResponseEntity.ok().body(teacherService.convertToDTO(updatedTeacher));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/teachers/{id}")
    public ResponseEntity<TeacherDTO> deleteTeacher(@PathVariable Long id) {
        return teacherService.findTeacherById(id)
                .map(teacher -> {
                    teacherService.deleteTeacher(id);
                    return ResponseEntity.ok().<TeacherDTO>build();
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
