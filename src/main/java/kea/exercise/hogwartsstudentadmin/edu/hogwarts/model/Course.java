package kea.exercise.hogwartsstudentadmin.edu.hogwarts.model;

import jakarta.persistence.*;

import java.util.List;

/**
 * The Course class is a model that represents a course in the Hogwarts Student Admin system.
 * It contains information about the subject, school year, current status, teacher, and students of the course.
 */
@Entity
public class Course {
    /**
     * The id of the course.
     * It is a unique identifier for the course and is generated automatically by the database.
     * It is the primary key of the course entity.
     * It is of type Long.
     * It is not nullable.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The subject of the course.
     * It is a string that represents the subject of the course.
     */
    private String subject;

    /**
     * The school year of the course.
     * It is an integer that represents the school year of the course.
     */
    private int schoolYear;

    /**
     * The current status of the course.
     * It is a boolean that represents whether the course is currently active or not.
     */
    private boolean isCurrent;

    /**
     * The teacher of the course.
     * It is a Teacher object that represents the teacher of the course.
     * It is a many-to-one relationship with the Teacher model.
     * It is fetched eagerly.
     */
    @ManyToOne(fetch = FetchType.EAGER)
    private Teacher teacher;

    /**
     * The students of the course.
     * It is a list of Student objects that represents the students of the course.
     * It is a many-to-many relationship with the Student model.
     * It is fetched eagerly.
     */
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Student> students;

    public Course() {
    }

    public Course(String subject, int schoolYear, boolean isCurrent, Teacher teacher, List<Student> students) {
        this.subject = subject;
        this.schoolYear = schoolYear;
        this.isCurrent = isCurrent;
        this.teacher = teacher;
        this.students = students;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getSchoolYear() {
        return schoolYear;
    }

    public void setSchoolYear(int schoolYear) {
        this.schoolYear = schoolYear;
    }

    public boolean isCurrent() {
        return isCurrent;
    }

    public void setCurrent(boolean current) {
        isCurrent = current;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", subject='" + subject + '\'' +
                ", schoolYear=" + schoolYear +
                ", isCurrent=" + isCurrent +
                ", teacher=" + teacher +
                ", students=" + students +
                '}';
    }
}

