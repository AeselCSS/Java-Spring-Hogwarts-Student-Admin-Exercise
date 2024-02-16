package kea.exercise.hogwartsstudentadmin.edu.hogwarts.services;

import kea.exercise.hogwartsstudentadmin.edu.hogwarts.models.Teacher;
import kea.exercise.hogwartsstudentadmin.edu.hogwarts.repositories.TeacherRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherServiceImpl implements TeacherService{
    @Autowired
    private TeacherRepository teacherRepository;

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
}
