package kea.exercise.hogwartsstudentadmin.edu.hogwarts.service;

import kea.exercise.hogwartsstudentadmin.edu.hogwarts.dto.TeacherDTO;
import kea.exercise.hogwartsstudentadmin.edu.hogwarts.model.EmpType;
import kea.exercise.hogwartsstudentadmin.edu.hogwarts.model.House;
import kea.exercise.hogwartsstudentadmin.edu.hogwarts.model.HouseName;
import kea.exercise.hogwartsstudentadmin.edu.hogwarts.model.Teacher;
import kea.exercise.hogwartsstudentadmin.edu.hogwarts.repository.HouseRepository;
import kea.exercise.hogwartsstudentadmin.edu.hogwarts.repository.TeacherRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static kea.exercise.hogwartsstudentadmin.edu.hogwarts.utility.StringUtility.toTitleCase;

@Service
public class TeacherServiceImpl implements TeacherService{
    private final TeacherRepository teacherRepository;
    private final HouseRepository houseRepository;

    public TeacherServiceImpl(TeacherRepository teacherRepository, HouseRepository houseRepository) {
        this.teacherRepository = teacherRepository;
        this.houseRepository = houseRepository;
    }

    @Override
    public List<Teacher> findAllTeachers() {
        return teacherRepository.findAll();
    }

    @Override
    public Optional<Teacher> findTeacherById(Long id) {
        return teacherRepository.findById(id);
    }

    @Override
    public Teacher saveTeacher(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    @Override
    public Teacher updateTeacher(Teacher teacher, Teacher updatedTeacher) {
        BeanUtils.copyProperties(updatedTeacher, teacher, "id");
        return teacherRepository.save(teacher);
    }

    @Override
    public void deleteTeacher(Long id) {
        teacherRepository.deleteById(id);
    }

    @Override
    public TeacherDTO convertToDTO(Teacher teacher) {
        String firstName = teacher.getFirstName();
        String middleName = teacher.getMiddleName();
        String lastName = teacher.getLastName();
        String house = toTitleCase(teacher.getHouse().getName().toString());
        boolean isHeadOfHouse = teacher.isHeadOfHouse();
        String employment = toTitleCase(teacher.getEmployment().toString());
        LocalDate employmentStart = teacher.getEmploymentStart();
        LocalDate employmentEnd = teacher.getEmploymentEnd();
        return new TeacherDTO(firstName, middleName, lastName, teacher.getDateOfBirth(), house, isHeadOfHouse, employment, employmentStart, employmentEnd);
    }

    @Override
    public Teacher convertFromDTO(TeacherDTO teacherDTO) {
        // Split the name into first, middle, and last name
        int firstSpace = teacherDTO.getName().indexOf(" ");
        int lastSpace = teacherDTO.getName().lastIndexOf(" ");
        String firstName = teacherDTO.getName().substring(0, firstSpace);
        String middleName = null;
        if (firstSpace != lastSpace) {
            middleName = teacherDTO.getName().substring(firstSpace + 1, lastSpace);
        }
        String lastName = teacherDTO.getName().substring(lastSpace + 1);

        LocalDate dateOfBirth = teacherDTO.getDateOfBirth();
        House house = houseRepository.findByName(HouseName.valueOf(teacherDTO.getHouse()));
        EmpType employment = EmpType.valueOf(teacherDTO.getEmployment().toUpperCase());
        boolean isHeadOfHouse = teacherDTO.isHeadOfHouse();
        LocalDate employmentStart = teacherDTO.getEmploymentStart();
        LocalDate employmentEnd = teacherDTO.getEmploymentEnd();

        return new Teacher(firstName, middleName, lastName, dateOfBirth, house, isHeadOfHouse, employment, employmentStart, employmentEnd);
    }
}
