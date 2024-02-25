package kea.exercise.hogwartsstudentadmin.edu.hogwarts.service;

import kea.exercise.hogwartsstudentadmin.edu.hogwarts.dto.*;
import kea.exercise.hogwartsstudentadmin.edu.hogwarts.model.Course;

import java.util.List;
/**
 * Interface for the CourseService class
    * @see kea.exercise.hogwartsstudentadmin.edu.hogwarts.service.CourseServiceImpl
 */

public interface CourseService {
    List<CourseResponseDTO> findAllCourses();
    CourseResponseDTO findCourseById(Long id);
    TeacherResponseDTO findTeacherByCourseId(Long courseId);
    List<StudentResponseDTO> findStudentsByCourseId(Long courseId);
    CourseResponseDTO saveCourse(CourseRequestDTO courseData);
    CourseResponseDTO addTeacherToCourse(Long id, TeacherRequestDTO teacher);
    CourseResponseDTO addStudentsToCourse(Long id, List<StudentRequestDTO> students);
    CourseResponseDTO updateCourse(Long id, CourseRequestDTO courseData);
    CourseResponseDTO updateCoursePartially(Long id, CourseRequestDTO courseData);
    CourseResponseDTO deleteCourse(Long id);
    StudentResponseDTO deleteStudentFromCourse(Long courseId, Long studentId);
    CourseResponseDTO toDTO(Course course);
    Course toEntity(CourseRequestDTO courseDTO);
}
