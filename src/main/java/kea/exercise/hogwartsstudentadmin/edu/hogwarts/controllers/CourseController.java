package kea.exercise.hogwartsstudentadmin.edu.hogwarts.controllers;

import kea.exercise.hogwartsstudentadmin.edu.hogwarts.models.Course;
import kea.exercise.hogwartsstudentadmin.edu.hogwarts.models.Student;
import kea.exercise.hogwartsstudentadmin.edu.hogwarts.models.Teacher;
import kea.exercise.hogwartsstudentadmin.edu.hogwarts.repositories.CourseRepository;
import kea.exercise.hogwartsstudentadmin.edu.hogwarts.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CourseController {
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private StudentRepository studentRepository;

    @GetMapping("/courses")
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @GetMapping("/courses/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable Long id) {
        return courseRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/courses/{id}/teacher") // returns the teacher object for the course with the specified id
    public ResponseEntity<Teacher> getTeacherByCourseId(@PathVariable Long id) {
        return courseRepository.findById(id)
                .map(courseObj -> ResponseEntity.ok().body(courseObj.getTeacher()))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/courses/{id}/students") // returns a list of students enrolled in the course with the specified id
    public ResponseEntity<List<Student>> getStudentsByCourseId(@PathVariable Long id) {
        return courseRepository.findById(id)
                .map(courseObj -> ResponseEntity.ok().body(courseObj.getStudents()))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/courses")
    public Course createCourse(Course course) {
        return courseRepository.save(course);
    }

    @PutMapping("/courses/{id}")
    // NOTE the use of <?> in the ResponseEntity type to allow for a response body of any type (in this case Course or String)
    public ResponseEntity<?> updateCourse(@PathVariable Long id, @RequestBody Course course) {
        return courseRepository.findById(id)
                .map(courseObj -> {
                    // Validate the teacher
                    if (!isValidTeacher(course.getTeacher())) {
                        return ResponseEntity.badRequest().body("Invalid teacher provided.");
                    }
                    // Validate the student list
                    if (!isValidStudentList(course.getStudents())) {
                        return ResponseEntity.badRequest().body("Invalid student list provided.");
                    }
                    // Update the course object with the new values
                    courseObj.setSubject(course.getSubject());
                    courseObj.setSchoolYear(course.getSchoolYear());
                    courseObj.setCurrent(course.isCurrent());
                    courseObj.setTeacher(course.getTeacher());
                    courseObj.setStudents(course.getStudents());
                    Course updatedCourse = courseRepository.save(courseObj);
                    return ResponseEntity.ok().body(updatedCourse);
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/courses/{id}/teacher") // updates the teacher for the course with the specified id
    public ResponseEntity<?> updateTeacherOnCourse(@PathVariable Long id, @RequestBody Teacher teacher) {
        return courseRepository.findById(id)
                .map(courseObj -> {
                    if (!isValidTeacher(teacher)) {
                        return ResponseEntity.badRequest().body("Invalid teacher provided.");
                    }
                    courseObj.setTeacher(teacher);
                    Course updatedCourse = courseRepository.save(courseObj);
                    return ResponseEntity.ok().body(updatedCourse);
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/courses/{id}/student{studentId}") // updates the list of students for the course with the specified id
    public ResponseEntity<?> addStudentToCourse(@PathVariable Long id, @PathVariable Long studentId) {
        return courseRepository.findById(id)
                .map(courseObj -> {
                    Optional<Student> student = studentRepository.findById(studentId);
                    if (student.isPresent()) {
                        if (!isValidStudent(student.get())) {
                            return ResponseEntity.badRequest().body("Invalid student provided.");
                        }
                        Student studentObj = student.get();
                        if (!isStudentEnrolledInCourse(courseObj, studentObj)) {
                            courseObj.getStudents().add(studentObj);
                            Course updatedCourse = courseRepository.save(courseObj);
                            return ResponseEntity.ok().body(updatedCourse);
                        } else {
                            return ResponseEntity.badRequest().body("Student is already enrolled in the course.");
                        }
                    } else {
                        // if the student is not found, return a 404 Not Found response
                        return ResponseEntity.notFound().build();
                    }
                    // if the course is not found, return a 404 Not Found response
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }


    @DeleteMapping("/courses/{id}")
    public ResponseEntity<Course> deleteCourse(@PathVariable Long id) {
        if (courseRepository.existsById(id)) {
            courseRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/courses/{id}/teacher") // removes the teacher from the course with the specified id
    public ResponseEntity<?> deleteTeacherFromCourse(@PathVariable Long id) {
        return courseRepository.findById(id)
                .map(courseObj -> {
                    courseObj.setTeacher(null);
                    Course updatedCourse = courseRepository.save(courseObj);
                    return ResponseEntity.ok().body(updatedCourse);
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/courses/{id}/student/{studentId}") // removes the student with the specified id from the course with the specified id
    public ResponseEntity<?> deleteStudentFromCourse(@PathVariable Long id, @PathVariable Long studentId) {
        return courseRepository.findById(id)
                .map(courseObj -> {
                    // get all enrolled students and remove the student with the specified id by matching the student id
                    courseObj.getStudents().removeIf(student -> student.getId().equals(studentId));
                    Course updatedCourse = courseRepository.save(courseObj);
                    return ResponseEntity.ok().body(updatedCourse);
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    private boolean isValidTeacher(Teacher teacher) {
        return teacher != null && teacher.getId() != null;
    }

    private boolean isValidStudentList(List<Student> students) {
        return students != null && students.stream().allMatch(student -> student.getId() != null);
    }

    private boolean isValidStudent(Student student) {
        return student != null && student.getId() != null;
    }

    private boolean isStudentEnrolledInCourse(Course course, Student student) {
        return course.getStudents().stream().anyMatch(s -> s.getId().equals(student.getId()));
    }

}
