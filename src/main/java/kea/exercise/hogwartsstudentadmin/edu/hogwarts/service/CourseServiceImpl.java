package kea.exercise.hogwartsstudentadmin.edu.hogwarts.service;

import kea.exercise.hogwartsstudentadmin.edu.hogwarts.exception.ResourceNotFoundHandler;
import kea.exercise.hogwartsstudentadmin.edu.hogwarts.model.Course;
import kea.exercise.hogwartsstudentadmin.edu.hogwarts.model.Student;
import kea.exercise.hogwartsstudentadmin.edu.hogwarts.model.Teacher;
import kea.exercise.hogwartsstudentadmin.edu.hogwarts.repository.CourseRepository;
import kea.exercise.hogwartsstudentadmin.edu.hogwarts.repository.StudentRepository;
import kea.exercise.hogwartsstudentadmin.edu.hogwarts.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements CourseService{
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private TeacherRepository teacherRepository;

    @Override
    public List<Course> findAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public Optional<Course> findCourseById(Long id) {
        return courseRepository.findById(id);
    }

    @Override
    public Optional<Teacher> findTeacherByCourseId(Long courseId) {
        return courseRepository.findById(courseId).map(Course::getTeacher);
    }

    @Override
    public Optional<List<Student>> findStudentsByCourseId(Long courseId) {
        return courseRepository.findById(courseId).map(Course::getStudents);
    }

    @Override
    public Course saveCourse(Course course) {
        if (course.getTeacher() != null) {
            Optional<Teacher> teacher = teacherRepository.findById(course.getTeacher().getId());
            teacher.ifPresent(course::setTeacher);
        }

        if (course.getStudents() != null && !course.getStudents().isEmpty()) {
            List<Long> studentIds = course.getStudents().stream()
                    .map(Student::getId)
                    .collect(Collectors.toList());
            List<Student> students = studentRepository.findAllById(studentIds);
            if (!students.isEmpty()) {
                course.setStudents(students);
            }
        }

        return courseRepository.save(course);
    }

    @Override
    public Course saveCourse(String subject, int schoolYear, boolean isCurrent, Long teacherId, List<Long> studentIds) {
        Course course = new Course();
        course.setSubject(subject);
        course.setSchoolYear(schoolYear);
        course.setCurrent(isCurrent);

        if (teacherId != null) {
            Teacher teacher = teacherRepository.findById(teacherId)
                    .orElseThrow(() -> new ResourceNotFoundHandler("Teacher not found with id: " + teacherId));
            course.setTeacher(teacher);
        }

        if (studentIds != null && !studentIds.isEmpty()) {
            List<Student> students = studentRepository.findAllById(studentIds);
            course.setStudents(students);
        }

        return courseRepository.save(course);
    }

    @Override
    public Course updateCourse(Long id, Course updatedCourse) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundHandler("Course not found with id " + id));

        course.setSubject(updatedCourse.getSubject());
        course.setSchoolYear(updatedCourse.getSchoolYear());
        course.setCurrent(updatedCourse.isCurrent());
        course.setTeacher(updatedCourse.getTeacher());
        course.setStudents(updatedCourse.getStudents());

        return courseRepository.save(course);
    }

    @Override
    public Course updateCourse(Long id, String subject, int schoolYear, boolean isCurrent, Long teacherId, List<Long> studentIds) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundHandler("Course not found with id: " + id));

        if (teacherId != null) {
            Teacher teacher = teacherRepository.findById(teacherId)
                    .orElseThrow(() -> new ResourceNotFoundHandler("Teacher not found with id: " + teacherId));
            course.setTeacher(teacher);
        } else {
            course.setTeacher(null); // clear teacher
        }

        if (studentIds != null) {
            if (!studentIds.isEmpty()) {
                List<Student> students = studentRepository.findAllById(studentIds);
                course.setStudents(students);
            } else {
                course.setStudents(new ArrayList<>()); // clear students
            }
        }

        course.setSubject(subject);
        course.setSchoolYear(schoolYear);
        course.setCurrent(isCurrent);

        return courseRepository.save(course);
    }


    @Override
    public void deleteCourse(Long id) {
        if (!courseRepository.existsById(id)) {
            throw new ResourceNotFoundHandler("Course not found with id " + id);
        }

        courseRepository.deleteById(id);
    }




    @Override
    public Course updateTeacherOnCourse(Long courseId, Teacher teacher) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundHandler("Course not found with id: " + courseId));

        if (teacher.getId() != null && teacherRepository.existsById(teacher.getId())) {
            course.setTeacher(teacher);
        } else {
            throw new ResourceNotFoundHandler("Teacher not found with id: " + teacher.getId());
        }

        return courseRepository.save(course);
    }

    @Override
    public Course addStudentToCourse(Long courseId, Long studentId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundHandler("Course not found with id: " + courseId));

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundHandler("Student not found with id: " + studentId));

        if (!course.getStudents().contains(student)) {
            course.getStudents().add(student);
            return courseRepository.save(course);
        } else {
            throw new IllegalArgumentException("Student is already enrolled in the course.");
        }
    }

    @Override
    public Course deleteTeacherFromCourse(Long courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundHandler("Course not found with id: " + courseId));

        course.setTeacher(null);
        return courseRepository.save(course);
    }

    @Override
    public Course deleteStudentFromCourse(Long courseId, Long studentId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundHandler("Course not found with id: " + courseId));

        boolean removed = course.getStudents().removeIf(student -> student.getId().equals(studentId));

        if (removed) {
            return courseRepository.save(course);
        } else {
            throw new ResourceNotFoundHandler("Student not found in course with id: " + studentId);
        }
    }
}