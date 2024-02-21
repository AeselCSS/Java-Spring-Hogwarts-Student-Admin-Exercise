package kea.exercise.hogwartsstudentadmin.edu.hogwarts.service;

import kea.exercise.hogwartsstudentadmin.edu.hogwarts.dto.StudentDTO;
import kea.exercise.hogwartsstudentadmin.edu.hogwarts.model.House;
import kea.exercise.hogwartsstudentadmin.edu.hogwarts.model.HouseName;
import kea.exercise.hogwartsstudentadmin.edu.hogwarts.model.Student;
import kea.exercise.hogwartsstudentadmin.edu.hogwarts.repository.HouseRepository;
import kea.exercise.hogwartsstudentadmin.edu.hogwarts.repository.StudentRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static kea.exercise.hogwartsstudentadmin.edu.hogwarts.utility.StringUtility.toTitleCase;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final HouseRepository houseRepository;

    public StudentServiceImpl(StudentRepository studentRepository, HouseRepository houseRepository) {
        this.studentRepository = studentRepository;
        this.houseRepository = houseRepository;
    }

    @Override
    public List<Student> findAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Optional<Student> findStudentById(Long id) {
        return studentRepository.findById(id);
    }

    @Override
    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Student updateStudent(Student student, Student updatedStudent) {
        BeanUtils.copyProperties(updatedStudent, student, "id");
        return studentRepository.save(student);
    }

    @Override
    public Student updateStudentPartially(Student student, Student updatedStudent) {
        return studentRepository.save(student);
    }

    @Override
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public StudentDTO convertToDTO(Student student) {
        String firstName = student.getFirstName();
        String middleName = student.getMiddleName();
        String lastName = student.getLastName();
        LocalDate dateOfBirth = student.getDateOfBirth();
        String house = toTitleCase(student.getHouse().getName().name());
        boolean isPrefect = student.isPrefect();
        int enrollmentYear = student.getEnrollmentYear();
        boolean isGraduated = student.isGraduated();
        int schoolYear = student.getSchoolYear();

        return new StudentDTO(firstName, middleName, lastName, dateOfBirth, house, isPrefect, enrollmentYear, isGraduated, schoolYear);
    }

    @Override
    public Student convertFromDTO(StudentDTO studentDTO) {
        // Split the full name into first name, middle names, and last name
        int firstSpace = studentDTO.getName().indexOf(" ");
        int lastSpace = studentDTO.getName().lastIndexOf(" ");
        String firstName = studentDTO.getName().substring(0, firstSpace);
        String middleName = null;
        if (firstSpace != lastSpace) {
            middleName = studentDTO.getName().substring(firstSpace + 1, lastSpace);
        }
        String lastName = studentDTO.getName().substring(lastSpace + 1);

        LocalDate dateOfBirth = studentDTO.getDateOfBirth();
        House house = houseRepository.findByName(HouseName.valueOf(studentDTO.getHouse()));
        boolean isPrefect = studentDTO.isPrefect();
        int enrollmentYear = studentDTO.getEnrollmentYear();
        boolean isGraduated = studentDTO.isGraduated();
        int schoolYear = studentDTO.getSchoolYear();

        return new Student(firstName, middleName, lastName, dateOfBirth, house, isPrefect, enrollmentYear, isGraduated, schoolYear);
    }
}


