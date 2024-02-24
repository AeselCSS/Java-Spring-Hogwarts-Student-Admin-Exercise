package kea.exercise.hogwartsstudentadmin.edu.hogwarts.service;

import kea.exercise.hogwartsstudentadmin.edu.hogwarts.dto.StudentRequestDTO;
import kea.exercise.hogwartsstudentadmin.edu.hogwarts.dto.StudentResponseDTO;
import kea.exercise.hogwartsstudentadmin.edu.hogwarts.exception.EntityNotFoundException;
import kea.exercise.hogwartsstudentadmin.edu.hogwarts.exception.ResourceNotFoundException;
import kea.exercise.hogwartsstudentadmin.edu.hogwarts.model.House;
import kea.exercise.hogwartsstudentadmin.edu.hogwarts.model.Student;
import kea.exercise.hogwartsstudentadmin.edu.hogwarts.repository.HouseRepository;
import kea.exercise.hogwartsstudentadmin.edu.hogwarts.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static kea.exercise.hogwartsstudentadmin.edu.hogwarts.utility.StringUtility.toFullName;
import static kea.exercise.hogwartsstudentadmin.edu.hogwarts.utility.StringUtility.toNameParts;


@Service
public class StudentServiceImpl implements StudentService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final StudentRepository studentRepository;
    private final HouseRepository houseRepository;

    public StudentServiceImpl(StudentRepository studentRepository, HouseRepository houseRepository) {
        this.studentRepository = studentRepository;
        this.houseRepository = houseRepository;
    }

    @Override
    public List<StudentResponseDTO> findAllStudents() {
        return studentRepository.findAll().stream().map(this::toDTO).toList();
    }

    @Override
    public StudentResponseDTO findStudentById(Long id) {
        return studentRepository.findById(id).map(this::toDTO).orElseThrow(() -> new EntityNotFoundException("Student", id));
    }

    @Override
    public StudentResponseDTO saveStudent(StudentRequestDTO student) {
        return toDTO(studentRepository.save(toEntity(student)));
    }

    @Override
    @Transactional
    public StudentResponseDTO updateStudent(StudentRequestDTO updatedStudent, Long id) {
        Optional<Student> studentOptional = studentRepository.findById(id);
        studentOptional.ifPresent(student -> {
            Student updatedStudentEntity = toEntity(updatedStudent);
            updatedStudentEntity.setId(id);
            BeanUtils.copyProperties(updatedStudentEntity, student);
            studentRepository.save(student);
        });
        return studentOptional.map(this::toDTO).orElseThrow(() -> new EntityNotFoundException("Student", id));
    }

    @Override
    @Transactional
    public StudentResponseDTO updateStudentPartially(StudentRequestDTO updatedStudent, Long id) {
        logger.info("Updating student partially: {}", updatedStudent);
        Optional<Student> studentOptional = studentRepository.findById(id);
        // if the student is present, copy the non-null properties from the updatedStudent to the student and save the student
        studentOptional.ifPresent(student -> {
            if(updatedStudent.name() != null) {
                String[] names = toNameParts(updatedStudent.name());
                student.setFirstName(names[0]);
                student.setMiddleName(names[1]);
                student.setLastName(names[2]);
            }
            if(updatedStudent.dateOfBirth() != null) student.setDateOfBirth(updatedStudent.dateOfBirth());
            if(updatedStudent.house() != null) {
                Optional<House> houseOptional = houseRepository.findById(updatedStudent.house());
                houseOptional.ifPresent(student::setHouse);
            }
            if(updatedStudent.isPrefect() != null) student.setPrefect(updatedStudent.isPrefect());
            if(updatedStudent.enrollmentYear() != null) student.setEnrollmentYear(updatedStudent.enrollmentYear());
            if(updatedStudent.graduationYear() != null) student.setGraduationYear(updatedStudent.graduationYear());
            if(updatedStudent.isGraduated() != null) student.setGraduated(updatedStudent.isGraduated());
            if(updatedStudent.schoolYear() != null) student.setSchoolYear(updatedStudent.schoolYear());

            studentRepository.save(student);
        });
        // return the student if present, otherwise throw an exception
        return studentOptional.map(this::toDTO).orElseThrow(() -> new EntityNotFoundException("Student", id));
    }

    @Override
    public StudentResponseDTO deleteStudent(Long id) {
        Optional<Student> studentOptional = studentRepository.findById(id);
        studentOptional.ifPresent(studentRepository::delete);
        return studentOptional.map(this::toDTO).orElseThrow(() -> new EntityNotFoundException("Student", id));
    }

    @Override
    public StudentResponseDTO toDTO(Student student) {
        logger.info("Converting student to DTO: {}", student);
        String name = null;
        if (student.getName() != null) {
            name = toFullName(student.getFirstName(), student.getMiddleName(), student.getLastName());
        }
        LocalDate dateOfBirth = student.getDateOfBirth();
        String house = student.getHouse().getName();
        Boolean isPrefect = student.isPrefect();
        Integer enrollmentYear = student.getEnrollmentYear();
        Integer graduationYear = student.getGraduationYear();
        Boolean isGraduated = student.isGraduated();
        Integer schoolYear = student.getSchoolYear();

        return new StudentResponseDTO(student.getId(), name, dateOfBirth, house, isPrefect, enrollmentYear, graduationYear, isGraduated, schoolYear);
    }

    @Override
    public Student toEntity(StudentRequestDTO studentDTO) {
        logger.info("Converting student DTO to entity: {}", studentDTO);
        String[] names = toNameParts(studentDTO.name());
        String firstName = names[0];
        String middleName = names[1];
        String lastName = names[2];
        LocalDate dateOfBirth = studentDTO.dateOfBirth();
        House house = houseRepository.findById(studentDTO.house()).orElseThrow(()-> new ResourceNotFoundException("House with name " + studentDTO.house() + " not found"));
        Boolean isPrefect = studentDTO.isPrefect();
        Integer enrollmentYear = studentDTO.enrollmentYear();
        Integer graduationYear = studentDTO.graduationYear();
        Boolean isGraduated = studentDTO.isGraduated();
        Integer schoolYear = studentDTO.schoolYear();
        return new Student(firstName, middleName, lastName, dateOfBirth, house, isPrefect, enrollmentYear, graduationYear, isGraduated, schoolYear);
    }
}


