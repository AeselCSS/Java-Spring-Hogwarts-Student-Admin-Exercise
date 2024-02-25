package kea.exercise.hogwartsstudentadmin.edu.hogwarts.service;

import kea.exercise.hogwartsstudentadmin.edu.hogwarts.dto.TeacherRequestDTO;
import kea.exercise.hogwartsstudentadmin.edu.hogwarts.dto.TeacherResponseDTO;
import kea.exercise.hogwartsstudentadmin.edu.hogwarts.model.Teacher;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * This interface defines the contract for the TeacherService.
 * It provides methods for CRUD operations on Teacher entities and for converting between Teacher and TeacherDTO objects.
 *
 * @see kea.exercise.hogwartsstudentadmin.edu.hogwarts.service.TeacherServiceImpl
 */
public interface TeacherService {
    /**
     * Retrieves all teachers from the database.
     *
     * @return a list of all teachers, each represented as a TeacherResponseDTO
     */
    List<TeacherResponseDTO> findAllTeachers();

    /**
     * Retrieves a teacher by their ID.
     *
     * @param id the ID of the teacher to retrieve
     * @return the teacher with the given ID, represented as a TeacherResponseDTO
     */
    TeacherResponseDTO findTeacherById(Long id);

    /**
     * Saves a new teacher to the database.
     *
     * @param teacher the teacher to save, represented as a TeacherRequestDTO
     * @return the saved teacher, represented as a TeacherResponseDTO
     */
    TeacherResponseDTO saveTeacher(TeacherRequestDTO teacher);

    /**
     * Updates an existing teacher in the database.
     *
     * @param updatedTeacher the updated teacher information, represented as a TeacherRequestDTO
     * @param id the ID of the teacher to update
     * @return the updated teacher, represented as a TeacherResponseDTO
     */
    TeacherResponseDTO updateTeacher(TeacherRequestDTO updatedTeacher, Long id);

    /**
     * Partially updates an existing teacher in the database.
     * Only the fields provided in the TeacherRequestDTO will be updated.
     *
     * @param updatedTeacher the updated teacher information, represented as a TeacherRequestDTO
     * @param id the ID of the teacher to update
     * @return the updated teacher, represented as a TeacherResponseDTO
     */
    @Transactional
    TeacherResponseDTO updateTeacherPartially(TeacherRequestDTO updatedTeacher, Long id);

    /**
     * Deletes a teacher from the database.
     *
     * @param id the ID of the teacher to delete
     * @return the deleted teacher, represented as a TeacherResponseDTO
     */
    TeacherResponseDTO deleteTeacher(Long id);

    /**
     * Converts a Teacher entity to a TeacherResponseDTO.
     *
     * @param teacher the teacher to convert
     * @return the teacher, represented as a TeacherResponseDTO
     */
    TeacherResponseDTO toDTO(Teacher teacher);

    /**
     * Converts a TeacherRequestDTO to a Teacher entity.
     *
     * @param teacherDTO the teacher DTO to convert
     * @return the teacher, represented as a Teacher entity
     */
    Teacher toEntity(TeacherRequestDTO teacherDTO);
}