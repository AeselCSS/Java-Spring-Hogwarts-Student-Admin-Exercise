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

/**
 * The CourseController class is a REST controller that handles HTTP requests for the Course model.
 * It is responsible for handling requests for the Course model and returning responses to the client.
 */
@RestController
public class CourseController {
    private final CourseService courseService;

    /**
     * Constructor for the CourseController class.
     * @param courseService The CourseService object that is used to handle the business logic for the Course model.
     */
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    /**
     * This method handles the HTTP GET request for the /courses endpoint.
     * It returns a list of all courses in the database.
     * @return A list of CourseResponseDTO objects.
     */

    @GetMapping("/courses")
    public List<CourseResponseDTO> getAllCourses() {
        return courseService.findAllCourses();
    }

    /**
     * This method handles the HTTP GET request for the /courses/{id} endpoint.
     * It returns a course with the specified id.
     * @param id The id of the course to return.
     * @return A CourseResponseDTO object.
     */

    @GetMapping("/courses/{id}")
    public ResponseEntity<CourseResponseDTO> getCourseById(@PathVariable Long id) {
        return ResponseEntity.ok(courseService.findCourseById(id));
    }

    /**
     * This method handles the HTTP GET request for the /courses/{id}/teacher endpoint.
     * It returns the teacher of the course with the specified id.
     * @param id The id of the course to return the teacher for.
     * @return A TeacherResponseDTO object.
     */

    @GetMapping("/courses/{id}/teacher")
    public ResponseEntity<TeacherResponseDTO> getTeacherByCourseId(@PathVariable Long id) {
        return ResponseEntity.ok(courseService.findTeacherByCourseId(id));
    }

    /**
     * This method handles the HTTP GET request for the /courses/{id}/students endpoint.
     * It returns a list of students enrolled in the course with the specified id.
     * @param id The id of the course to return the students for.
     * @return A list of StudentResponseDTO objects.
     */

    @GetMapping("/courses/{id}/students")
    public ResponseEntity<List<StudentResponseDTO>> getStudentsByCourseId(@PathVariable Long id) {
        return ResponseEntity.ok(courseService.findStudentsByCourseId(id));
    }

    /**
     * This method handles the HTTP POST request for the /courses endpoint.
     * It creates a new course with the specified data.
     * @param courseData The data for the new course.
     * @return A CourseResponseDTO object.
     */

    @PostMapping("/courses")
    public ResponseEntity<CourseResponseDTO> createCourse(@RequestBody CourseRequestDTO courseData) {
        return ResponseEntity.ok(courseService.saveCourse(courseData));
    }

    /**
     * This method handles the HTTP POST request for the /courses/{id}/teacher endpoint.
     * It adds a teacher to the course with the specified id.
     * @param id The id of the course to add the teacher to.
     * @param teacher The data for the teacher to add to the course.
     * @return A CourseResponseDTO object.
     */

    @PostMapping("/courses/{id}/teacher")
    public ResponseEntity<CourseResponseDTO> addTeacherToCourse(@PathVariable Long id, @RequestBody TeacherRequestDTO teacher) {
        return ResponseEntity.ok(courseService.addTeacherToCourse(id, teacher));
    }

    /**
     * This method handles the HTTP POST request for the /courses/{id}/students endpoint.
     * It adds students to the course with the specified id.
     * @param id The id of the course to add the students to.
     * @param students The data for the students to add to the course.
     * @return A CourseResponseDTO object.
     */

    @PostMapping("/courses/{id}/students")
    public ResponseEntity<CourseResponseDTO> addStudentsToCourse(@PathVariable Long id, @RequestBody List<StudentRequestDTO> students) {
        return ResponseEntity.ok(courseService.addStudentsToCourse(id, students));
    }

    /**
     * This method handles the HTTP PUT request for the /courses/{id} endpoint.
     * It updates the course with the specified id.
     * @param id The id of the course to update.
     * @param courseData The new data for the course.
     * @return A CourseResponseDTO object.
     */

    @PutMapping("/courses/{id}")
    public ResponseEntity<CourseResponseDTO> updateCourse(@PathVariable Long id, @RequestBody CourseRequestDTO courseData) {
        return ResponseEntity.ok(courseService.updateCourse(id, courseData));
    }

    /**
     * This method handles the HTTP PATCH request for the /courses/{id} endpoint.
     * It partially updates the course with the specified id.
     * @param id The id of the course to update.
     * @param courseData The new data for the course.
     * @return A CourseResponseDTO object.
     */

    @PatchMapping("/courses/{id}")
    public ResponseEntity<CourseResponseDTO> updateCoursePartially(@PathVariable Long id, @RequestBody CourseRequestDTO courseData) {
        return ResponseEntity.ok(courseService.updateCoursePartially(id, courseData));
    }

    /**
     * This method handles the HTTP DELETE request for the /courses/{id} endpoint.
     * It deletes the course with the specified id.
     * @param id The id of the course to delete.
     * @return A CourseResponseDTO object.
     */

    @DeleteMapping("/courses/{id}")
    public ResponseEntity<CourseResponseDTO> deleteCourse(@PathVariable Long id) {
        return ResponseEntity.ok(courseService.deleteCourse(id));
    }

    /**
     * This method handles the HTTP DELETE request for the /courses/{id}/teacher endpoint.
     * It removes the teacher from the course with the specified id.
     * @param id The id of the course to remove the teacher from.
     * @return A CourseResponseDTO object.
     */

    @DeleteMapping("/courses/{id}/student/{studentId}")
    public ResponseEntity<StudentResponseDTO> deleteStudentFromCourse(@PathVariable Long id, @PathVariable Long studentId) {
        return ResponseEntity.ok(courseService.deleteStudentFromCourse(id, studentId));
    }
}
