package kea.exercise.hogwartsstudentadmin.edu.hogwarts.service;

import kea.exercise.hogwartsstudentadmin.edu.hogwarts.dto.TeacherRequestDTO;
import kea.exercise.hogwartsstudentadmin.edu.hogwarts.dto.TeacherResponseDTO;
import kea.exercise.hogwartsstudentadmin.edu.hogwarts.exception.EntityNotFoundException;
import kea.exercise.hogwartsstudentadmin.edu.hogwarts.model.EmpType;
import kea.exercise.hogwartsstudentadmin.edu.hogwarts.model.House;
import kea.exercise.hogwartsstudentadmin.edu.hogwarts.model.Teacher;
import kea.exercise.hogwartsstudentadmin.edu.hogwarts.repository.CourseRepository;
import kea.exercise.hogwartsstudentadmin.edu.hogwarts.repository.HouseRepository;
import kea.exercise.hogwartsstudentadmin.edu.hogwarts.repository.TeacherRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static kea.exercise.hogwartsstudentadmin.edu.hogwarts.utility.StringUtility.*;

/**
 * Service class for the Teacher entity
 */
@Service
public class TeacherServiceImpl implements TeacherService{
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final TeacherRepository teacherRepository;
    private final HouseRepository houseRepository;
    private final CourseRepository courseRepository;

    /**
     * Constructor for the TeacherService class
     * @param teacherRepository The TeacherRepository object that is used to handle the data access logic for the Teacher model
     * @param houseRepository The HouseRepository object that is used to handle the data access logic for the House model
     * @param courseRepository The CourseRepository object that is used to handle the data access logic for the Course model
     */
    public TeacherServiceImpl(TeacherRepository teacherRepository, HouseRepository houseRepository, CourseRepository courseRepository) {
        this.teacherRepository = teacherRepository;
        this.houseRepository = houseRepository;
        this.courseRepository = courseRepository;
    }

    /**
     * Method to find all teachers
     * @return a list of all teachers
     */
    @Override
    public List<TeacherResponseDTO> findAllTeachers() {
        return teacherRepository.findAll().stream().map(this::toDTO).toList();
    }

    /**
     * Method to find a teacher by id
     * @param id the id of the teacher to find
     * @return a teacher
     */
    @Override
    public TeacherResponseDTO findTeacherById(Long id) {
        return teacherRepository.findById(id).map(this::toDTO).orElseThrow(() -> new EntityNotFoundException("Teacher", id));
    }

    /**
     * Method to save a teacher
     * @param teacher the teacher to save
     * @return a teacher
     */
    @Override
    public TeacherResponseDTO saveTeacher(TeacherRequestDTO teacher) {
        return toDTO(teacherRepository.save(toEntity(teacher)));
    }

    /**
     * Method to update a teacher
     * @param updatedTeacher the updated teacher information
     * @param id the id of the teacher to update
     * @return a teacher
     */
    @Override
    @Transactional
    public TeacherResponseDTO updateTeacher(TeacherRequestDTO updatedTeacher, Long id) {
        return teacherRepository.findById(id).map(teacher -> {
            Teacher updatedTeacherEntity = toEntity(updatedTeacher);
            updatedTeacherEntity.setId(id);
            BeanUtils.copyProperties(updatedTeacherEntity, teacher);
            return toDTO(teacherRepository.save(teacher));
        }).orElseThrow(() -> new EntityNotFoundException("Teacher", id));
    }

    /**
     * Method to update a teacher partially
     * @param updatedTeacher the updated teacher information
     * @param id the id of the teacher to update
     * @return a teacher
     */
    @Override
    @Transactional
    public TeacherResponseDTO updateTeacherPartially(TeacherRequestDTO updatedTeacher, Long id) {
        Optional<Teacher> teacherOptional = teacherRepository.findById(id);
        teacherOptional.ifPresent(teacher -> {
            if(updatedTeacher.name() != null) {
                String[] names = toNameParts(updatedTeacher.name());
                teacher.setFirstName(names[0]);
                teacher.setMiddleName(names[1]);
                teacher.setLastName(names[2]);
            }
            if(updatedTeacher.dateOfBirth() != null) teacher.setDateOfBirth(updatedTeacher.dateOfBirth());
            if(updatedTeacher.house() != null) {
                Optional<House> houseOptional = houseRepository.findById(updatedTeacher.house());
                houseOptional.ifPresent(teacher::setHouse);
            }
            if(updatedTeacher.isHeadOfHouse() != null) teacher.setHeadOfHouse(updatedTeacher.isHeadOfHouse());
            if(updatedTeacher.employment() != null) teacher.setEmployment(EmpType.valueOf(updatedTeacher.employment().toUpperCase()));
            if(updatedTeacher.employmentStart() != null) teacher.setEmploymentStart(updatedTeacher.employmentStart());
            if(updatedTeacher.employmentEnd() != null) teacher.setEmploymentEnd(updatedTeacher.employmentEnd());

            teacherRepository.save(teacher);
        });
        return teacherOptional.map(this::toDTO).orElseThrow(() -> new EntityNotFoundException("Teacher", id));
    }

    /**
     * Method to delete a teacher
     * @param id the id of the teacher to delete
     * @return a teacher
     */
    @Override
    @Transactional
    public TeacherResponseDTO deleteTeacher(Long id) {
        return teacherRepository.findById(id).map(teacher -> {
            List<Long> courseIds = courseRepository.findCourseIdsByTeacherId(id);
            courseIds.forEach(courseId -> courseRepository.deleteTeacherFromCourse(id));
            teacherRepository.delete(teacher);
            return toDTO(teacher);
        }).orElseThrow(() -> new EntityNotFoundException("Teacher", id));
    }

    /**
     * Method to convert a teacher to a DTO
     * @param teacher the teacher to convert
     * @return a teacher DTO
     */
    @Override
    public TeacherResponseDTO toDTO(Teacher teacher) {
        logger.info("Converting teacher to DTO: {}", teacher);
        String name = toFullName(teacher.getFirstName(), teacher.getMiddleName(), teacher.getLastName());
        LocalDate dateOfbirth = teacher.getDateOfBirth();
        String house = teacher.getHouse().getName();
        Boolean isHeadOfHouse = teacher.isHeadOfHouse();
        String employment = toTitleCase(String.valueOf(teacher.getEmployment()));
        LocalDate employmentStart = teacher.getEmploymentStart();
        LocalDate employmentEnd = teacher.getEmploymentEnd();

        return new TeacherResponseDTO(teacher.getId(), name, dateOfbirth, house, isHeadOfHouse, employment, employmentStart, employmentEnd);
    }

    /**
     * Method to convert a teacher DTO to an entity
     * @param teacherDTO the teacher DTO to convert
     * @return a teacher
     */
    @Override
    public Teacher toEntity(TeacherRequestDTO teacherDTO) {
        logger.info("Converting teacher DTO to entity: {}", teacherDTO);
        String[] names = toNameParts(teacherDTO.name());
        String firstName = names[0];
        String middleName = names[1];
        String lastName = names[2];
        LocalDate dateOfBirth = teacherDTO.dateOfBirth();
        House house = houseRepository.findById(teacherDTO.house()).orElse(null);
        Boolean isHeadOfHouse = teacherDTO.isHeadOfHouse();
        EmpType employment = EmpType.valueOf(teacherDTO.employment().toUpperCase());
        LocalDate employmentStart = teacherDTO.employmentStart();
        LocalDate employmentEnd = teacherDTO.employmentEnd();
        return new Teacher(firstName, middleName, lastName, dateOfBirth, house, isHeadOfHouse, employment, employmentStart, employmentEnd);
    }
}
