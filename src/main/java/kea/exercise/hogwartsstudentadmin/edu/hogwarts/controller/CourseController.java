package kea.exercise.hogwartsstudentadmin.edu.hogwarts.controller;

import kea.exercise.hogwartsstudentadmin.edu.hogwarts.dto.*;
import kea.exercise.hogwartsstudentadmin.edu.hogwarts.model.Course;
import kea.exercise.hogwartsstudentadmin.edu.hogwarts.model.Student;
import kea.exercise.hogwartsstudentadmin.edu.hogwarts.model.Teacher;
import kea.exercise.hogwartsstudentadmin.edu.hogwarts.service.CourseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/courses")
    public List<CourseResponseDTO> getAllCourses() {
        return courseService.findAllCourses();
    }

    @GetMapping("/courses/{id}")
    public ResponseEntity<CourseResponseDTO> getCourseById(@PathVariable Long id) {
        return ResponseEntity.ok(courseService.findCourseById(id));
    }

    @GetMapping("/courses/{id}/teacher")
    public ResponseEntity<TeacherResponseDTO> getTeacherByCourseId(@PathVariable Long id) {
        return ResponseEntity.ok(courseService.findTeacherByCourseId(id));
    }

    @GetMapping("/courses/{id}/students")
    public ResponseEntity<List<StudentResponseDTO>> getStudentsByCourseId(@PathVariable Long id) {
        return ResponseEntity.ok(courseService.findStudentsByCourseId(id));
    }

    @PostMapping("/courses")
    public ResponseEntity<CourseResponseDTO> createCourse(@RequestBody CourseRequestDTO courseData) {
        return ResponseEntity.ok(courseService.saveCourse(courseData));
    }

    @PostMapping("/courses/{id}/teacher")
    public ResponseEntity<CourseResponseDTO> addTeacherToCourse(@PathVariable Long id, @RequestBody TeacherRequestDTO teacher) {
        return ResponseEntity.ok(courseService.addTeacherToCourse(id, teacher));
    }

    @PostMapping("/courses/{id}/students")
    public ResponseEntity<CourseResponseDTO> addStudentsToCourse(@PathVariable Long id, @RequestBody List<StudentRequestDTO> students) {
        return ResponseEntity.ok(courseService.addStudentsToCourse(id, students));
    }

    @PutMapping("/courses/{id}")
    public ResponseEntity<CourseResponseDTO> updateCourse(@PathVariable Long id, @RequestBody CourseRequestDTO courseData) {
        return ResponseEntity.ok(courseService.updateCourse(id, courseData));
    }

    @PatchMapping("/courses/{id}")
    public ResponseEntity<CourseResponseDTO> updateCoursePartially(@PathVariable Long id, @RequestBody CourseRequestDTO courseData) {
        return ResponseEntity.ok(courseService.updateCoursePartially(id, courseData));
    }

    @DeleteMapping("/courses/{id}")
    public ResponseEntity<CourseResponseDTO> deleteCourse(@PathVariable Long id) {
        return ResponseEntity.ok(courseService.deleteCourse(id));
    }

    @DeleteMapping("/courses/{id}/student/{studentId}")
    public ResponseEntity<StudentResponseDTO> deleteStudentFromCourse(@PathVariable Long id, @PathVariable Long studentId) {
        return ResponseEntity.ok(courseService.deleteStudentFromCourse(id, studentId));
    }
}
