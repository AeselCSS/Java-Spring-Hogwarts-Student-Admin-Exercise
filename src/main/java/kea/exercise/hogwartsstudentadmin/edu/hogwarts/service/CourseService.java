package kea.exercise.hogwartsstudentadmin.edu.hogwarts.service;

import kea.exercise.hogwartsstudentadmin.edu.hogwarts.dto.*;
import kea.exercise.hogwartsstudentadmin.edu.hogwarts.model.Course;

import java.util.List;

/**
 * This interface defines the contract for the CourseService.
 * It provides methods for managing courses in the Hogwarts Student Admin system.
 * The methods include operations for finding, saving, updating, and deleting courses,
 * as well as operations for managing the relationship between courses and teachers/students.
 */
public interface CourseService {

    /**
     * Retrieves all courses.
     * @return a list of all courses.
     */
    List<CourseResponseDTO> findAllCourses();

    /**
     * Retrieves a course by its id.
     * @param id the id of the course to retrieve.
     * @return the course with the given id.
     */
    CourseResponseDTO findCourseById(Long id);

    /**
     * Retrieves the teacher of a course by the course's id.
     * @param courseId the id of the course.
     * @return the teacher of the course with the given id.
     */
    TeacherResponseDTO findTeacherByCourseId(Long courseId);

    /**
     * Retrieves all students of a course by the course's id.
     * @param courseId the id of the course.
     * @return a list of students of the course with the given id.
     */
    List<StudentResponseDTO> findStudentsByCourseId(Long courseId);

    /**
     * Saves a new course.
     * @param courseData the data of the course to save.
     * @return the saved course.
     */
    CourseResponseDTO saveCourse(CourseRequestDTO courseData);

    /**
     * Adds a teacher to a course.
     * @param id the id of the course.
     * @param teacher the teacher to add to the course.
     * @return the updated course.
     */
    CourseResponseDTO addTeacherToCourse(Long id, TeacherRequestDTO teacher);

    /**
     * Adds students to a course.
     * @param id the id of the course.
     * @param students the students to add to the course.
     * @return the updated course.
     */
    CourseResponseDTO addStudentsToCourse(Long id, List<StudentRequestDTO> students);

    /**
     * Updates a course.
     * @param id the id of the course to update.
     * @param courseData the new data of the course.
     * @return the updated course.
     */
    CourseResponseDTO updateCourse(Long id, CourseRequestDTO courseData);

    /**
     * Partially updates a course.
     * @param id the id of the course to update.
     * @param courseData the new data of the course.
     * @return the updated course.
     */
    CourseResponseDTO updateCoursePartially(Long id, CourseRequestDTO courseData);

    /**
     * Deletes a course.
     * @param id the id of the course to delete.
     * @return the deleted course.
     */
    CourseResponseDTO deleteCourse(Long id);

    /**
     * Deletes a student from a course.
     * @param courseId the id of the course.
     * @param studentId the id of the student to delete from the course.
     * @return the updated course.
     */
    StudentResponseDTO deleteStudentFromCourse(Long courseId, Long studentId);

    /**
     * Converts a Course entity to a CourseResponseDTO.
     * @param course the Course entity to convert.
     * @return the converted CourseResponseDTO.
     */
    CourseResponseDTO toDTO(Course course);

    /**
     * Converts a CourseRequestDTO to a Course entity.
     * @param courseDTO the CourseRequestDTO to convert.
     * @return the converted Course entity.
     */
    Course toEntity(CourseRequestDTO courseDTO);
}