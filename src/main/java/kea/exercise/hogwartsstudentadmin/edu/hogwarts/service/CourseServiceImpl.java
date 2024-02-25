package kea.exercise.hogwartsstudentadmin.edu.hogwarts.service;

import kea.exercise.hogwartsstudentadmin.edu.hogwarts.dto.*;
import kea.exercise.hogwartsstudentadmin.edu.hogwarts.exception.EntityNotFoundException;
import kea.exercise.hogwartsstudentadmin.edu.hogwarts.exception.ResourceNotFoundException;
import kea.exercise.hogwartsstudentadmin.edu.hogwarts.exception.SchoolYearMismatchException;
import kea.exercise.hogwartsstudentadmin.edu.hogwarts.model.Course;
import kea.exercise.hogwartsstudentadmin.edu.hogwarts.model.Student;
import kea.exercise.hogwartsstudentadmin.edu.hogwarts.model.Teacher;
import kea.exercise.hogwartsstudentadmin.edu.hogwarts.repository.CourseRepository;
import kea.exercise.hogwartsstudentadmin.edu.hogwarts.repository.StudentRepository;
import kea.exercise.hogwartsstudentadmin.edu.hogwarts.repository.TeacherRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static kea.exercise.hogwartsstudentadmin.edu.hogwarts.utility.StringUtility.toFullName;

/**
 * Service class for managing courses.
 */
@Service
public class CourseServiceImpl implements CourseService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;
    private final StudentServiceImpl studentService;
    private final TeacherServiceImpl teacherService;

    /**
     * Constructor for CourseServiceImpl.
     *
     * @param courseRepository  the repository for courses
     * @param studentRepository the repository for students
     * @param teacherRepository the repository for teachers
     * @param studentService    the service for students
     * @param teacherService    the service for teachers
     */
    public CourseServiceImpl(CourseRepository courseRepository, StudentRepository studentRepository, TeacherRepository teacherRepository, StudentServiceImpl studentService, TeacherServiceImpl teacherService) {
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
        this.studentService = studentService;
        this.teacherService = teacherService;
    }

    /**
     * Fetches all courses.
     *
     * @return a list of all courses
     */
    @Override
    public List<CourseResponseDTO> findAllCourses() {
        return courseRepository.findAll().stream().map(this::toDTO).toList();
    }

    /**
     * Fetches a course by its ID.
     *
     * @param id the ID of the course
     * @return the course with the given ID
     * @throws EntityNotFoundException if no course with the given ID is found
     */
    @Override
    public CourseResponseDTO findCourseById(Long id) {
        return courseRepository.findById(id).map(this::toDTO).orElseThrow(() -> new EntityNotFoundException("Course", id));
    }

    /**
     * Fetches the teacher of a course by the course's ID.
     *
     * @param courseId the ID of the course
     * @return the teacher of the course with the given ID
     * @throws EntityNotFoundException if no course with the given ID is found
     */
    @Override
    public TeacherResponseDTO findTeacherByCourseId(Long courseId) {
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new EntityNotFoundException("Course", courseId));
        return teacherRepository.findById(course.getTeacher().getId()).map(teacherService::toDTO).orElseThrow(() -> new EntityNotFoundException("Teacher", course.getTeacher().getId()));
    }

    /**
     * Fetches the students of a course by the course's ID.
     *
     * @param courseId the ID of the course
     * @return a list of the students of the course with the given ID
     * @throws EntityNotFoundException if no course with the given ID is found
     */
    @Override
    public List<StudentResponseDTO> findStudentsByCourseId(Long courseId) {
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new EntityNotFoundException("Course", courseId));
        return course.getStudents().stream().map(studentService::toDTO).toList();
    }

    /**
     * Saves a course.
     *
     * @param courseData the data of the course to save
     * @return the saved course
     */
    @Override
    public CourseResponseDTO saveCourse(CourseRequestDTO courseData) {
        // find teacher and students
        Teacher teacher = findTeacher(courseData);
        List<Student> students = findStudents(courseData);
        // check if students are in the same school year as the course
        checkStudentSchoolYear(courseData.schoolYear(), students);
        // create course
        Course course = new Course(courseData.subject(), courseData.schoolYear(), courseData.isCurrent(), teacher, students);
        return toDTO(courseRepository.save(course));
    }

    /**
     * Adds a teacher to a course.
     *
     * @param id      the ID of the course
     * @param teacher the teacher to add
     * @return the course with the added teacher
     * @throws EntityNotFoundException if no course or teacher with the given ID is found
     */
    @Override
    @Transactional
    public CourseResponseDTO addTeacherToCourse(Long id, TeacherRequestDTO teacher) {
        Optional<Course> courseOptional = courseRepository.findById(id);
        Optional<Teacher> teacherOptional = teacherRepository.findById(teacher.id());
        if (courseOptional.isPresent() && teacherOptional.isPresent()) {
            Course course = courseOptional.get();
            Teacher teacherEntity = teacherOptional.get();
            course.setTeacher(teacherEntity);
            courseRepository.save(course);
            return toDTO(course);
        }
        throw new EntityNotFoundException("Course or Teacher", id);
    }

    /**
     * Adds students to a course.
     *
     * @param id       the ID of the course
     * @param students the students to add
     * @return the course with the added students
     * @throws EntityNotFoundException if no course with the given ID is found
     */
    @Override
    @Transactional
    public CourseResponseDTO addStudentsToCourse(Long id, List<StudentRequestDTO> students) {
        Optional<Course> courseOptional = courseRepository.findById(id);
        if (courseOptional.isPresent()) {
            Course course = courseOptional.get();
            List<Student> studentEntities = students.stream().map(student -> studentRepository.findById(student.id()).orElseThrow(() -> new EntityNotFoundException("Student", student.id()))).toList();

            // check if students are in the same school year as the course
            checkStudentSchoolYear(course.getSchoolYear(), studentEntities);

            // add students to course and save
            course.getStudents().addAll(studentEntities);
            courseRepository.save(course);
            return toDTO(course);
        }
        throw new EntityNotFoundException("Course", id);
    }

    /**
     * Updates a course.
     *
     * @param id         the ID of the course
     * @param courseData the new data of the course
     * @return the updated course
     * @throws EntityNotFoundException if no course with the given ID is found
     */
    @Override
    @Transactional
    public CourseResponseDTO updateCourse(Long id, CourseRequestDTO courseData) {
        Optional<Course> courseOptional = courseRepository.findById(id);
        courseOptional.ifPresent(course -> {
            Course updatedCourseEntity = toEntity(courseData);
            updatedCourseEntity.setId(id);
            // check if students are in the same school year as the course
            checkStudentSchoolYear(updatedCourseEntity.getSchoolYear(), updatedCourseEntity.getStudents());
            // update course and save
            BeanUtils.copyProperties(updatedCourseEntity, course);
            courseRepository.save(course);
        });
        return courseOptional.map(this::toDTO).orElseThrow(() -> new EntityNotFoundException("Course", id));
    }

    /**
     * Partially updates a course.
     *
     * @param id         the ID of the course
     * @param courseData the new data of the course
     * @return the updated course
     * @throws EntityNotFoundException if no course with the given ID is found
     */
    @Override
    @Transactional
    public CourseResponseDTO updateCoursePartially(Long id, CourseRequestDTO courseData) {
        logger.info("Partially updating course: {}", courseData);
        Optional<Course> courseOptional = courseRepository.findById(id);
        courseOptional.ifPresent(course -> {
            if (courseData.subject() != null) course.setSubject(courseData.subject());
            if (courseData.schoolYear() != null) {
                course.setSchoolYear(courseData.schoolYear());
            }
            if (courseData.isCurrent() != null) course.setCurrent(courseData.isCurrent());
            if (courseData.teacherId() != null) {
                Optional<Teacher> teacherOptional = teacherRepository.findById(courseData.teacherId());
                teacherOptional.ifPresent(course::setTeacher);
            }
            //
            if (courseData.studentIds() != null) {
                List<Student> studentEntities = courseData.studentIds().stream().map(studentId -> studentRepository.findById(studentId).orElseThrow(() -> new EntityNotFoundException("Student", studentId))).toList();
                if (courseData.schoolYear() != null) {
                    checkStudentSchoolYear(courseData.schoolYear(), studentEntities);
                } else {
                    checkStudentSchoolYear(course.getSchoolYear(), studentEntities);
                }
                // clear students and add updated list of students
                course.getStudents().clear();
                studentEntities.forEach(course.getStudents()::add);
            }
            courseRepository.save(course);
        });
        return courseOptional.map(this::toDTO).orElseThrow(() -> new EntityNotFoundException("Course", id));
    }

    /**
     * Deletes a course.
     *
     * @param id the ID of the course
     * @return the deleted course
     * @throws EntityNotFoundException if no course with the given ID is found
     */
    @Override
    public CourseResponseDTO deleteCourse(Long id) {
        Optional<Course> courseOptional = courseRepository.findById(id);
        courseOptional.ifPresent(courseRepository::delete);
        return courseOptional.map(this::toDTO).orElseThrow(() -> new EntityNotFoundException("Course", id));
    }

    /**
     * Deletes a student from a course.
     *
     * @param courseId  the ID of the course
     * @param studentId the ID of the student
     * @return the deleted student
     * @throws ResourceNotFoundException if no course with the given ID or no student with the given ID is found
     */
    @Override
    public StudentResponseDTO deleteStudentFromCourse(Long courseId, Long studentId) {
        Optional<Course> courseOptional = courseRepository.findById(courseId);
        Optional<Student> studentOptional = studentRepository.findById(studentId);
        if (courseOptional.isPresent() && studentOptional.isPresent()) {
            Course course = courseOptional.get();
            Student student = studentOptional.get();
            course.getStudents().remove(student);
            courseRepository.save(course);
            return studentService.toDTO(student);
        }
        throw new ResourceNotFoundException("Course with id " + courseId + " or Student with id " + studentId + " not found.");
    }

    /**
     * Converts a course to a CourseResponseDTO.
     *
     * @param course the course to convert
     * @return the converted course
     */
    @Override
    public CourseResponseDTO toDTO(Course course) {
        Long id = course.getId();
        String subject = course.getSubject();
        Integer schoolYear = course.getSchoolYear();
        boolean isCurrent = course.isCurrent();
        TeacherResponseDTO teacher = null;
        if (course.getTeacher() != null) {
            teacher = teacherService.toDTO(course.getTeacher());
        }
        List<StudentResponseDTO> students = course.getStudents().stream().map(studentService::toDTO).toList();

        return new CourseResponseDTO(id, subject, schoolYear, isCurrent, teacher, students);
    }

    /**
     * Converts a CourseRequestDTO to a course.
     *
     * @param courseDTO the CourseRequestDTO to convert
     * @return the converted course
     */
    @Override
    public Course toEntity(CourseRequestDTO courseDTO) {
        String subject = courseDTO.subject();
        int schoolYear = courseDTO.schoolYear();
        boolean isCurrent = courseDTO.isCurrent();
        Teacher teacher = null;
        if (courseDTO.teacherId() != null) {
            teacher = teacherRepository.findById(courseDTO.teacherId()).orElseThrow(() -> new EntityNotFoundException("Teacher", courseDTO.teacherId()));
        }
        List<Student> students = studentRepository.findAllById(courseDTO.studentIds());
        return new Course(subject, schoolYear, isCurrent, teacher, students);
    }

    /**
     * Finds a teacher.
     *
     * @param courseData the data of the course
     * @return the found teacher
     */
    private Teacher findTeacher(CourseRequestDTO courseData) {
        Teacher teacher = null;
        if (courseData.teacherId() != null) {
            teacher = teacherRepository.findById(courseData.teacherId()).orElseThrow(() -> new EntityNotFoundException("Teacher", courseData.teacherId()));
        } else if (courseData.teacherName() != null) {
            teacher = teacherRepository.findByFullName(courseData.teacherName()).orElseThrow(() -> new ResourceNotFoundException("Teacher with name " + courseData.teacherName() + " not found."));
        }
        return teacher;
    }

    /**
     * Finds students.
     *
     * @param courseData the data of the course
     * @return a list of the found students
     */
    private List<Student> findStudents(CourseRequestDTO courseData) {
        List<Student> students = new ArrayList<>();
        if (courseData.studentIds() != null) {
            for (Long id : courseData.studentIds()) {
                Student student = studentRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Student", id));
                students.add(student);
            }
        } else if (courseData.studentNames() != null) {
            for (String name : courseData.studentNames()) {
                Student student = studentRepository.findByFullName(name).orElseThrow(() -> new ResourceNotFoundException("Student with name " + name + " not found."));
                students.add(student);
            }
        }
        return students;
    }

    /**
     * Checks if students are in the same school year as a course.
     *
     * @param courseSchoolYear the school year of the course
     * @param students         the students to check
     * @throws SchoolYearMismatchException if a student is not in the same school year as the course
     */
    //TODO: refactor to collect all students with mismatched school year and throw exception with all students
    // that do not match the course school year instead of throwing exception for the first mismatched student.
    // This may require changes to the exception class to accept a list of students.
    private void checkStudentSchoolYear(Integer courseSchoolYear, List<Student> students) {
        for (Student student : students) {
            if (!Objects.equals(student.getSchoolYear(), courseSchoolYear)) {
                throw new SchoolYearMismatchException("Student " + toFullName(student.getFirstName(), student.getMiddleName(), student.getLastName()) + " is in school year " + student.getSchoolYear() + " and cannot be added to a course in school year " + courseSchoolYear);
            }
        }
    }
}