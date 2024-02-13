package kea.exercise.hogwartsstudentadmin.edu.hogwarts.repositories;

import kea.exercise.hogwartsstudentadmin.edu.hogwarts.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
