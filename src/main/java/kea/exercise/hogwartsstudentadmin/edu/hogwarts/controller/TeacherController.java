package kea.exercise.hogwartsstudentadmin.edu.hogwarts.controller;

import kea.exercise.hogwartsstudentadmin.edu.hogwarts.dto.TeacherRequestDTO;
import kea.exercise.hogwartsstudentadmin.edu.hogwarts.dto.TeacherResponseDTO;
import kea.exercise.hogwartsstudentadmin.edu.hogwarts.service.TeacherService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The TeacherController class is a REST controller that handles HTTP requests for the Teacher model.
 * It is responsible for handling requests for the Teacher model and returning responses to the client.
 */
@RestController
public class TeacherController {
    private final TeacherService teacherService;

    /**
     * Constructor for the TeacherController class.
     * @param teacherService The TeacherService object that is used to handle the business logic for the Teacher model.
     */
    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    /**
     * This method handles the HTTP GET request for the /teachers endpoint.
     * It returns a list of all teachers in the database.
     * @return A list of TeacherResponseDTO objects.
     */
    @GetMapping("/teachers")
    public List<TeacherResponseDTO> getAllTeachers() {
        return teacherService.findAllTeachers();
    }

    /**
     * This method handles the HTTP GET request for the /teachers/{id} endpoint.
     * It returns a teacher with the specified id.
     * @param id The id of the teacher to return.
     * @return A TeacherResponseDTO object.
     */
    @GetMapping("/teachers/{id}")
    public ResponseEntity<TeacherResponseDTO> getTeacherById(@PathVariable Long id) {
        return ResponseEntity.ok(teacherService.findTeacherById(id));
    }

    /**
     * This method handles the HTTP POST request for the /teachers endpoint.
     * It creates a new teacher in the database.
     * @param teacher The TeacherRequestDTO object that contains the data for the new teacher.
     * @return A TeacherResponseDTO object.
     */
    @PostMapping("/teachers")
    public ResponseEntity<TeacherResponseDTO> createTeacher(@RequestBody TeacherRequestDTO teacher) {
        return ResponseEntity.ok(teacherService.saveTeacher(teacher));
    }

    /**
     * This method handles the HTTP PUT request for the /teachers/{id} endpoint.
     * It updates a teacher with the specified id.
     * @param teacher The TeacherRequestDTO object that contains the updated data for the teacher.
     * @param id The id of the teacher to update.
     * @return A TeacherResponseDTO object.
     */
    @PutMapping("/teachers/{id}")
    public ResponseEntity<TeacherResponseDTO> updateTeacher(@RequestBody TeacherRequestDTO teacher, @PathVariable Long id) {
        return ResponseEntity.ok(teacherService.updateTeacher(teacher, id));
    }

    /**
     * This method handles the HTTP PATCH request for the /teachers/{id} endpoint.
     * It updates a teacher with the specified id.
     * @param teacher The TeacherRequestDTO object that contains the updated data for the teacher.
     * @param id The id of the teacher to update.
     * @return A TeacherResponseDTO object.
     */
    @PatchMapping("/teachers/{id}")
    public ResponseEntity<TeacherResponseDTO> updateTeacherPartially(@RequestBody TeacherRequestDTO teacher, @PathVariable Long id) {
        return ResponseEntity.ok(teacherService.updateTeacherPartially(teacher, id));
    }

    /**
     * This method handles the HTTP DELETE request for the /teachers/{id} endpoint.
     * It deletes a teacher with the specified id.
     * @param id The id of the teacher to delete.
     * @return A TeacherResponseDTO object.
     */
    @DeleteMapping("/teachers/{id}")
    public ResponseEntity<TeacherResponseDTO> deleteTeacher(@PathVariable Long id) {
        return ResponseEntity.ok(teacherService.deleteTeacher(id));
    }
}
