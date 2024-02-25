package kea.exercise.hogwartsstudentadmin.edu.hogwarts.service;

import kea.exercise.hogwartsstudentadmin.edu.hogwarts.dto.TeacherRequestDTO;
import kea.exercise.hogwartsstudentadmin.edu.hogwarts.dto.TeacherResponseDTO;
import kea.exercise.hogwartsstudentadmin.edu.hogwarts.model.Teacher;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Interface for the TeacherService class
 * @see kea.exercise.hogwartsstudentadmin.edu.hogwarts.service.TeacherServiceImpl
 */
public interface TeacherService {
    List<TeacherResponseDTO> findAllTeachers();
    TeacherResponseDTO findTeacherById(Long id);
    TeacherResponseDTO saveTeacher(TeacherRequestDTO teacher);
    TeacherResponseDTO updateTeacher(TeacherRequestDTO updatedTeacher, Long id);

    @Transactional
    TeacherResponseDTO updateTeacherPartially(TeacherRequestDTO updatedTeacher, Long id);

    TeacherResponseDTO deleteTeacher(Long id);
    TeacherResponseDTO toDTO(Teacher teacher);
    Teacher toEntity(TeacherRequestDTO teacherDTO);
}

