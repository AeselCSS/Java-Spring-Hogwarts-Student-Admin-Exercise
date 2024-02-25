package kea.exercise.hogwartsstudentadmin.edu.hogwarts.service;

import kea.exercise.hogwartsstudentadmin.edu.hogwarts.dto.*;
import kea.exercise.hogwartsstudentadmin.edu.hogwarts.model.Course;

import java.util.List;
/**
 * Interface for the CourseService class
    * @see kea.exercise.hogwartsstudentadmin.edu.hogwarts.service.CourseServiceImpl
 */

public interface CourseService {
    /**
     * Method to find all courses
     * @return a list of all courses
     */
    List<CourseResponseDTO> findAllCourses();

    /**
     * Method to find a course by id
     * @param id
     * @return a course
     */
    CourseResponseDTO findCourseById(Long id);

    /**
     * Method to find a teacher by course id
     * @param courseId
     * @return a teacher
     */
    TeacherResponseDTO findTeacherByCourseId(Long courseId);

    /**
     * Method to find all students by course id
     * @param courseId
     * @return a list of students
     */
    List<StudentResponseDTO> findStudentsByCourseId(Long courseId);

    /**
     * Method to save a course
     * @param courseData
     * @return a course
     */
    CourseResponseDTO saveCourse(CourseRequestDTO courseData);

    /**
     * Method to add a teacher to a course
     * @param id
     * @param teacher
     * @return a course
     */
    CourseResponseDTO addTeacherToCourse(Long id, TeacherRequestDTO teacher);

    /**
     * Method to add students to a course
     * @param id
     * @param students
     * @return a course
     */
    CourseResponseDTO addStudentsToCourse(Long id, List<StudentRequestDTO> students);

    /**
     * Method to update a course
     * @param id
     * @param courseData
     * @return a course
     */
    CourseResponseDTO updateCourse(Long id, CourseRequestDTO courseData);

    /**
     * Method to update a course partially
     * @param id
     * @param courseData
     * @return a course
     */
    CourseResponseDTO updateCoursePartially(Long id, CourseRequestDTO courseData);

    /**
     * Method to delete a course
     * @param id
     * @return a course
     */
    CourseResponseDTO deleteCourse(Long id);

    /**
     * Method to delete a student from a course
     * @param courseId
     * @param studentId
     * @return a course
     */
    StudentResponseDTO deleteStudentFromCourse(Long courseId, Long studentId);

    /**
     * Method to convert a course to a DTO
     * @param course
     * @return a course DTO
     */
    CourseResponseDTO toDTO(Course course);

    /**
     * Method to convert a course DTO to an entity
     * @param courseDTO
     * @return a course
     */
    Course toEntity(CourseRequestDTO courseDTO);
}
