package kea.exercise.hogwartsstudentadmin.edu.hogwarts.service;

import kea.exercise.hogwartsstudentadmin.edu.hogwarts.model.Course;
import kea.exercise.hogwartsstudentadmin.edu.hogwarts.model.Student;
import kea.exercise.hogwartsstudentadmin.edu.hogwarts.model.Teacher;
import java.util.List;
import java.util.Optional;

public interface CourseService {
    List<Course> findAllCourses();
    Optional<Course> findCourseById(Long id);
    Optional<Teacher> findTeacherByCourseId(Long courseId);
    Optional<List<Student>> findStudentsByCourseId(Long courseId);
    Course saveCourse(Course course);
    Course saveCourse(String subject, int schoolYear, boolean isCurrent, Long teacherId, List<Long> studentIds);
    Course updateCourse(Long id, Course course);
    Course updateCourse(Long id, String subject, int schoolYear, boolean isCurrent, Long teacherId, List<Long> studentIds);
    Course updateTeacherOnCourse(Long courseId, Teacher teacher);
    Course addStudentToCourse(Long courseId, Long studentId);
    void deleteCourse(Long id);
    Course deleteTeacherFromCourse(Long courseId);
    Course deleteStudentFromCourse(Long courseId, Long studentId);
}
