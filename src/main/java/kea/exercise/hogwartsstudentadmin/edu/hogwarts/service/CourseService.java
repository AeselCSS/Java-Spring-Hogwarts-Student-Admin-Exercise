package kea.exercise.hogwartsstudentadmin.edu.hogwarts.service;

import kea.exercise.hogwartsstudentadmin.edu.hogwarts.dto.*;
import kea.exercise.hogwartsstudentadmin.edu.hogwarts.model.Course;
import kea.exercise.hogwartsstudentadmin.edu.hogwarts.model.Student;
import kea.exercise.hogwartsstudentadmin.edu.hogwarts.model.Teacher;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
