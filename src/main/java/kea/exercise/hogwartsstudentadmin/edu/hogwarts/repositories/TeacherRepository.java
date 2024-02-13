package kea.exercise.hogwartsstudentadmin.edu.hogwarts.repositories;

import kea.exercise.hogwartsstudentadmin.edu.hogwarts.models.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
}
