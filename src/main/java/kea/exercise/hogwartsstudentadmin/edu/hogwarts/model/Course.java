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

    /**
     * Default constructor for the Course class.
     */
    public Course() {
    }

    /**
     * Constructor for the Course class with all fields.
     * @param subject The subject of the course.
     * @param schoolYear The school year of the course.
     * @param isCurrent The current status of the course.
     * @param teacher The teacher of the course.
     * @param students The students of the course.
     */
    public Course(String subject, int schoolYear, boolean isCurrent, Teacher teacher, List<Student> students) {
        this.subject = subject;
        this.schoolYear = schoolYear;
        this.isCurrent = isCurrent;
        this.teacher = teacher;
        this.students = students;
    }

    /**
     * Getter for the id of the course.
     * @return The id of the course.
     */
    public Long getId() {
        return id;
    }

    /**
     * Setter for the id of the course.
     * @param id The new id of the course.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Getter for the subject of the course.
     * @return The subject of the course.
     */
    public String getSubject() {
        return subject;
    }

    /**
     * Setter for the subject of the course.
     * @param subject The new subject of the course.
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * Getter for the school year of the course.
     * @return The school year of the course.
     */
    public int getSchoolYear() {
        return schoolYear;
    }

    /**
     * Setter for the school year of the course.
     * @param schoolYear The new school year of the course.
     */
    public void setSchoolYear(int schoolYear) {
        this.schoolYear = schoolYear;
    }

    /**
     * Getter for the current status of the course.
     * @return The current status of the course.
     */
    public boolean isCurrent() {
        return isCurrent;
    }

    /**
     * Setter for the current status of the course.
     * @param current The new current status of the course.
     */
    public void setCurrent(boolean current) {
        isCurrent = current;
    }

    /**
     * Getter for the teacher of the course.
     * @return The teacher of the course.
     */
    public Teacher getTeacher() {
        return teacher;
    }

    /**
     * Setter for the teacher of the course.
     * @param teacher The new teacher of the course.
     */
    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    /**
     * Getter for the students of the course.
     * @return The students of the course.
     */
    public List<Student> getStudents() {
        return students;
    }

    /**
     * Setter for the students of the course.
     * @param students The new students of the course.
     */
    public void setStudents(List<Student> students) {
        this.students = students;
    }

    /**
     * Returns a string representation of the course.
     * @return A string representation of the course.
     */
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