package kea.exercise.hogwartsstudentadmin.edu.hogwarts.service;

import kea.exercise.hogwartsstudentadmin.edu.hogwarts.dto.TeacherDTO;
import kea.exercise.hogwartsstudentadmin.edu.hogwarts.model.Teacher;
import java.util.List;
import java.util.Optional;

public interface TeacherService {
    List<Teacher> findAllTeachers();
    Optional<Teacher> findTeacherById(Long id);
    Teacher saveTeacher(Teacher teacher);
    Teacher updateTeacher(Teacher teacher, Teacher updatedTeacher);
    void deleteTeacher(Long id);
    TeacherDTO convertToDTO(Teacher teacher);
    Teacher convertFromDTO(TeacherDTO teacherDTO);
}

