package kea.exercise.hogwartsstudentadmin.edu.hogwarts.config;

import kea.exercise.hogwartsstudentadmin.edu.hogwarts.model.*;
import kea.exercise.hogwartsstudentadmin.edu.hogwarts.repository.CourseRepository;
import kea.exercise.hogwartsstudentadmin.edu.hogwarts.repository.HouseRepository;
import kea.exercise.hogwartsstudentadmin.edu.hogwarts.repository.StudentRepository;
import kea.exercise.hogwartsstudentadmin.edu.hogwarts.repository.TeacherRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class InitData implements ApplicationRunner {
    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;
    private final HouseRepository houseRepository;


    House gryffindor;
    House hufflepuff;
    House ravenclaw;
    House slytherin;

    public InitData(CourseRepository courseRepository, StudentRepository studentRepository, TeacherRepository teacherRepository, HouseRepository houseRepository) {
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
        this.houseRepository = houseRepository;
    }


    @Override
    public void run(ApplicationArguments args) {
        initHouses();
        initStudents();
        initTeachers();
        initCourses();
    }

    private void initCourses() {
        if (courseRepository.count() == 0) {
            List<Teacher> teachers = teacherRepository.findAll();
            List<Student> students = studentRepository.findAll();
            List<Course> courses = new ArrayList<>();

            courses.add(new Course("Transfiguration", 1, true, teachers.get(0), students.subList(0, 12)));
            courses.add(new Course("Herbology", 1, true, teachers.get(1), students.subList(0, 12)));
            courses.add(new Course("Charms", 1, true, teachers.get(2), students.subList(0, 12)));
            courses.add(new Course("Potions", 1, true, teachers.get(3), students.subList(0, 12)));
            courses.add(new Course("Defense Against the Dark Arts", 3, true, teachers.get(6), students.subList(0, 12)));

            courseRepository.saveAll(courses);
        }
    }

    private void initTeachers() {
        if (teacherRepository.count() == 0) {
            List<Teacher> teachers = new ArrayList<>();

            EmpType tenured = EmpType.TENURED;
            EmpType temporary = EmpType.TEMPORARY;

            teachers.add(new Teacher("Minerva", "McGonagall", LocalDate.of(1935, Month.OCTOBER, 4), gryffindor, true, tenured, LocalDate.of(1960, Month.SEPTEMBER, 1), null));
            teachers.add(new Teacher("Pomona", "Sprout", LocalDate.of(1931, Month.MAY, 15), hufflepuff, true, tenured, LocalDate.of(1975, Month.SEPTEMBER, 1), null));
            teachers.add(new Teacher("Filius", "Flitwick", LocalDate.of(1935, Month.OCTOBER, 17), ravenclaw, true, tenured, LocalDate.of(1975, Month.SEPTEMBER, 1), null));
            teachers.add(new Teacher("Severus", "Snape", LocalDate.of(1960, Month.JANUARY, 9), slytherin, true, tenured, LocalDate.of(1981, Month.SEPTEMBER, 1), null));
            teachers.add(new Teacher("Horace", "Slughorn", LocalDate.of(1882, Month.APRIL, 28), slytherin, false, temporary, LocalDate.of(1996, Month.SEPTEMBER, 1), LocalDate.of(1997, Month.JUNE, 30)));
            teachers.add(new Teacher("Alastor", "Moody", LocalDate.of(1960, Month.JANUARY, 1), gryffindor, false, temporary, LocalDate.of(1994, Month.SEPTEMBER, 1), LocalDate.of(1997, Month.JUNE, 30)));
            teachers.add(new Teacher("Remus", "Lupin", LocalDate.of(1960, Month.MARCH, 10), gryffindor, false, temporary, LocalDate.of(1993, Month.SEPTEMBER, 1), LocalDate.of(1997, Month.JUNE, 30)));

            teacherRepository.saveAll(teachers);
        }
    }

    private void initStudents() {
        if (studentRepository.count() == 0) {
            List<Student> students = new ArrayList<>();
            // get houses from the database
            students.add(new Student("Harry", "James", "Potter", LocalDate.of(1980, Month.JULY, 31), gryffindor, false, 1991, false, 1));
            students.add(new Student("Hermione", "Jean", "Granger", LocalDate.of(1979, Month.SEPTEMBER, 19), gryffindor, false, 1991, false, 1));
            students.add(new Student("Ronald", "Bilius", "Weasley", LocalDate.of(1980, Month.MARCH, 1), gryffindor, false, 1991, false, 1));
            students.add(new Student("Draco", "Lucius", "Malfoy", LocalDate.of(1980, Month.JUNE, 5), slytherin, false, 1991, false, 1));
            students.add(new Student("Pansy", "Parkinson", LocalDate.of(1980, Month.JULY, 4), slytherin, false, 1991, false, 1));
            students.add(new Student("Vincent", "Crabbe", LocalDate.of(1980, Month.JANUARY, 1), slytherin, false, 1991, false, 1));
            students.add(new Student("Padma", "Patil", LocalDate.of(1980, Month.APRIL, 1), ravenclaw, false, 1991, false, 1));
            students.add(new Student("Terry", "Boot", LocalDate.of(1980, Month.MAY, 1), ravenclaw, false, 1991, false, 1));
            students.add(new Student("Michael", "Corner", LocalDate.of(1980, Month.JUNE, 1), ravenclaw, false, 1991, false, 1));
            students.add(new Student("Justin", "Finch-Fletchley", LocalDate.of(1980, Month.JULY, 1), hufflepuff, false, 1991, false, 1));
            students.add(new Student("Hannah", "Abbott", LocalDate.of(1980, Month.AUGUST, 1), hufflepuff, false, 1991, false, 1));
            students.add(new Student("Susan", "Bones", LocalDate.of(1980, Month.SEPTEMBER, 1), hufflepuff, false, 1991, false, 1));

            studentRepository.saveAll(students);
        }
    }

    private void initHouses() {
        if (houseRepository.count() == 0) {
            House gryffindor = new House(HouseName.GRYFFINDOR, "Godric Gryffindor", new ArrayList<>(Arrays.asList("Scarlet", "Gold")));
            House hufflepuff = new House(HouseName.HUFFLEPUFF, "Helga Hufflepuff", new ArrayList<>(Arrays.asList("Yellow", "Black")));
            House ravenclaw = new House(HouseName.RAVENCLAW, "Rowena Ravenclaw", new ArrayList<>(Arrays.asList("Blue", "Bronze")));
            House slytherin = new House(HouseName.SLYTHERIN, "Salazar Slytherin", new ArrayList<>(Arrays.asList("Green", "Silver")));

            this.gryffindor = gryffindor;
            this.hufflepuff = hufflepuff;
            this.ravenclaw = ravenclaw;
            this.slytherin = slytherin;

            houseRepository.save(gryffindor);
            houseRepository.save(hufflepuff);
            houseRepository.save(ravenclaw);
            houseRepository.save(slytherin);
        }
    }
}
