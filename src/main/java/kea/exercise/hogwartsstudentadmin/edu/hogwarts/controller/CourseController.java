package kea.exercise.hogwartsstudentadmin.edu.hogwarts.controller;

import kea.exercise.hogwartsstudentadmin.edu.hogwarts.model.Course;
import kea.exercise.hogwartsstudentadmin.edu.hogwarts.model.Student;
import kea.exercise.hogwartsstudentadmin.edu.hogwarts.model.Teacher;
import kea.exercise.hogwartsstudentadmin.edu.hogwarts.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping("/courses")
    public List<Course> getAllCourses() {
        return courseService.findAllCourses();
    }

    @GetMapping("/courses/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable Long id) {
        return courseService.findCourseById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/courses/{id}/teacher")
    public ResponseEntity<Teacher> getTeacherByCourseId(@PathVariable Long id) {
        return courseService.findTeacherByCourseId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/courses/{id}/students")
    public ResponseEntity<List<Student>> getStudentsByCourseId(@PathVariable Long id) {
        return courseService.findStudentsByCourseId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/courses")
    public Course createCourse(@RequestBody Map<String, Object> courseData) {
        String subject = (String) courseData.get("subject");
        int schoolYear = (Integer) courseData.get("schoolYear");
        boolean isCurrent = (Boolean) courseData.get("isCurrent");
        Long teacherId = courseData.get("teacher") != null ? ((Number) courseData.get("teacher")).longValue() : null;
        List<Long> studentIds = courseData.get("students") != null ? ((List<Number>) courseData.get("students")).stream()
                .map(Number::longValue)
                .collect(Collectors.toList()) : null;

        return courseService.saveCourse(subject, schoolYear, isCurrent, teacherId, studentIds);
    }


    @PutMapping("/courses/{id}")
    public ResponseEntity<?> updateCourse(@PathVariable Long id, @RequestBody Map<String, Object> courseData) {
        String subject = (String) courseData.get("subject");
        int schoolYear = (Integer) courseData.get("schoolYear");
        boolean isCurrent = (Boolean) courseData.get("isCurrent");
        Long teacherId = courseData.get("teacher") != null ? ((Number) courseData.get("teacher")).longValue() : null;
        List<Long> studentIds = courseData.get("students") != null ? ((List<Number>) courseData.get("students")).stream()
                .map(Number::longValue)
                .collect(Collectors.toList()) : null;

        try {
            Course updatedCourse = courseService.updateCourse(id, subject, schoolYear, isCurrent, teacherId, studentIds);
            return ResponseEntity.ok().body(updatedCourse);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/courses/{id}/teacher")
    public ResponseEntity<?> updateTeacherOnCourse(@PathVariable Long id, @RequestBody Teacher teacher) {
        try {
            Course updatedCourse = courseService.updateTeacherOnCourse(id, teacher);
            return ResponseEntity.ok().body(updatedCourse);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/courses/{id}/student/{studentId}")
    public ResponseEntity<?> addStudentToCourse(@PathVariable Long id, @PathVariable Long studentId) {
        try {
            Course updatedCourse = courseService.addStudentToCourse(id, studentId);
            return ResponseEntity.ok().body(updatedCourse);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/courses/{id}")
    public ResponseEntity<?> deleteCourse(@PathVariable Long id) {
        try {
            courseService.deleteCourse(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/courses/{id}/teacher")
    public ResponseEntity<?> deleteTeacherFromCourse(@PathVariable Long id) {
        try {
            Course updatedCourse = courseService.deleteTeacherFromCourse(id);
            return ResponseEntity.ok().body(updatedCourse);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/courses/{id}/student/{studentId}")
    public ResponseEntity<?> deleteStudentFromCourse(@PathVariable Long id, @PathVariable Long studentId) {
        try {
            Course updatedCourse = courseService.deleteStudentFromCourse(id, studentId);
            return ResponseEntity.ok().body(updatedCourse);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
