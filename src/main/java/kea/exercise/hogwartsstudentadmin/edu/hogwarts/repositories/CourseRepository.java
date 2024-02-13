package kea.exercise.hogwartsstudentadmin.edu.hogwarts.repositories;

import kea.exercise.hogwartsstudentadmin.edu.hogwarts.models.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
