package kea.exercise.hogwartsstudentadmin.edu.hogwarts.controller;

import kea.exercise.hogwartsstudentadmin.edu.hogwarts.dto.StudentRequestDTO;
import kea.exercise.hogwartsstudentadmin.edu.hogwarts.dto.StudentResponseDTO;
import kea.exercise.hogwartsstudentadmin.edu.hogwarts.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The StudentController class is a REST controller that handles HTTP requests for the Student model.
 * It is responsible for handling requests for the Student model and returning responses to the client.
 */
@RestController
public class StudentController {

    private final StudentService studentService;

    /**
     * Constructor for the StudentController class.
     * @param studentService The StudentService object that is used to handle the business logic for the Student model.
     */
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    /**
     * This method handles the HTTP GET request for the /students endpoint.
     * It returns a list of all students in the database.
     * @return A list of StudentResponseDTO objects.
     */
    @GetMapping("/students")
    public List<StudentResponseDTO> getAllStudents() {
        return studentService.findAllStudents();
    }

    /**
     * This method handles the HTTP GET request for the /students/{id} endpoint.
     * It returns a student with the specified id.
     * @param id The id of the student to return.
     * @return A StudentResponseDTO object.
     */
    @GetMapping("/students/{id}")
    public ResponseEntity<StudentResponseDTO> getStudentById(@PathVariable Long id) {
        return ResponseEntity.ok(studentService.findStudentById(id));
    }

    /**
     * This method handles the HTTP POST request for the /students endpoint.
     * It creates a new student in the database.
     * @param studentDTO The StudentRequestDTO object that contains the data for the new student.
     * @return A StudentResponseDTO object.
     */
    @PostMapping("/students")
    public ResponseEntity<StudentResponseDTO> createStudent(@RequestBody StudentRequestDTO studentDTO) {
        return ResponseEntity.ok(studentService.saveStudent(studentDTO));
    }

    /**
     * This method handles the HTTP PUT request for the /students/{id} endpoint.
     * @param id The id of the student to update.
     * @param studentDTO The StudentRequestDTO object that contains the updated data for the student.
     * @return A StudentResponseDTO object.
     */
    @PutMapping("/students/{id}")
    public ResponseEntity<StudentResponseDTO> updateStudent(@PathVariable Long id, @RequestBody StudentRequestDTO studentDTO) {
        return ResponseEntity.ok(studentService.updateStudent(studentDTO, id));
    }

    /**
     * This method handles the HTTP PATCH request for the /students/{id} endpoint.
     * @param id The id of the student to update.
     * @param studentDTO The StudentRequestDTO object that contains the updated data for the student.
     * @return A StudentResponseDTO object.
     */
    @PatchMapping("/students/{id}")
    public ResponseEntity<StudentResponseDTO> updateStudentPartially(@PathVariable Long id, @RequestBody StudentRequestDTO studentDTO) {
        return ResponseEntity.ok(studentService.updateStudentPartially(studentDTO, id));
    }

    /**
     * This method handles the HTTP DELETE request for the /students/{id} endpoint.
     * It deletes a student with the specified id from the database.
     * @param id The id of the student to delete.
     * @return A StudentResponseDTO object.
     */
    @DeleteMapping("/students/{id}")
    public ResponseEntity<StudentResponseDTO> deleteStudent(@PathVariable Long id) {
        return ResponseEntity.ok(studentService.deleteStudent(id));
    }
}