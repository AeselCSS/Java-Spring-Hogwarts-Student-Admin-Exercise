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
    /**
     * Method to find all teachers
     * @return a list of all teachers
     */
    List<TeacherResponseDTO> findAllTeachers();

    /**
     * Method to find a teacher by id
     * @param id
     * @return a teacher
     */
    TeacherResponseDTO findTeacherById(Long id);

    /**
     * Method to save a teacher
     * @param teacher
     * @return a teacher
     */
    TeacherResponseDTO saveTeacher(TeacherRequestDTO teacher);

    /**
     * Method to update a teacher
     * @param updatedTeacher
     * @param id
     * @return a teacher
     */
    TeacherResponseDTO updateTeacher(TeacherRequestDTO updatedTeacher, Long id);

    /**
     * Method to update a teacher partially
     * @param updatedTeacher
     * @param id
     * @return a teacher
     */
    @Transactional
    TeacherResponseDTO updateTeacherPartially(TeacherRequestDTO updatedTeacher, Long id);

    /**
     * Method to delete a teacher
     * @param id
     * @return a teacher
     */
    TeacherResponseDTO deleteTeacher(Long id);

    /**
     * Method to convert a teacher to a DTO
     * @param teacher
     * @return a teacherResponseDTO
     */
    TeacherResponseDTO toDTO(Teacher teacher);

    /**
     * Method to convert a teacherRequestDTO to an entity
     * @param teacherDTO
     * @return a teacher
     */
    Teacher toEntity(TeacherRequestDTO teacherDTO);
}

