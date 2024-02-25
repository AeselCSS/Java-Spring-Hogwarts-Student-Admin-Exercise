package kea.exercise.hogwartsstudentadmin.edu.hogwarts.repository;

import kea.exercise.hogwartsstudentadmin.edu.hogwarts.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * CourseRepository interface
 * This interface extends JpaRepository and is used to perform CRUD operations on the Course entity
 * It also contains custom queries to find course ids by student id and teacher id
 * It also contains custom queries to delete a student from a course and a teacher from a course
 */
public interface CourseRepository extends JpaRepository<Course, Long> {
    /**
     * Custom query to find course ids by student id
     * @param studentId
     * @return List of course ids
     */
    @Query("SELECT c.id FROM Course c JOIN c.students s WHERE s.id = :studentId")
    List<Long> findCourseIdsByStudentId(@Param("studentId") Long studentId);

    /**
     * Custom query to find course ids by teacher id
     * @param teacherId
     * @return List of course ids
     */
    @Query("SELECT c.id FROM Course c WHERE c.teacher.id = :teacherId")
    List<Long> findCourseIdsByTeacherId(@Param("teacherId") Long teacherId);

    /**
     * Custom query to delete a student from a course
     * @param courseId
     * @param studentId
     */
    @Modifying
    @Query(value = "DELETE FROM course_student cs WHERE cs.course_id = :courseId AND cs.student_id = :studentId", nativeQuery = true)
    void deleteStudentFromCourse(@Param("courseId") Long courseId, @Param("studentId") Long studentId);

    /**
     * Custom query to delete a teacher from a course
     * @param teacherId
     */
    @Modifying
    @Query(value = "DELETE FROM Course c WHERE c.teacher.id = :teacherId", nativeQuery = true)
    void deleteTeacherFromCourse(@Param("teacherId") Long teacherId);
}
