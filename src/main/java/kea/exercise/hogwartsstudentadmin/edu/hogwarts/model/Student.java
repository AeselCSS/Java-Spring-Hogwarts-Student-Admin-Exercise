package kea.exercise.hogwartsstudentadmin.edu.hogwarts.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "First name is mandatory")
    @Size(min = 2, max = 50, message = "First name must be between 2 and 50 characters")
    private String firstName;

    @Size(max = 75, message = "Middle name must be less than 50 characters")
    private String middleName;

    @NotBlank(message = "Last name is mandatory")
    @Size(min = 2, max = 50, message = "Last name must be between 2 and 50 characters")
    private String lastName;

    @Past(message = "Date of birth must be in the past")
    private LocalDate dateOfBirth;


    @NotNull(message = "House is mandatory")
    // define column name to be house instead of house_id or house_name in the student table
    @JoinColumn(name = "house")
    private @ManyToOne(fetch = FetchType.EAGER) House house;

    @NotNull(message = "Prefect status is mandatory")
    private boolean isPrefect;

    @NotNull(message = "Enrollment year is mandatory")
    @Min(value = 1900, message = "Enrollment year must be after 1900")
    private Integer enrollmentYear;

    private Integer graduationYear;

    @NotNull(message = "Graduated status is mandatory")
    private boolean isGraduated;

    @Min(value = 1, message = "School year must be at least 1")
    @Max(value = 7, message = "School year must be at most 7")
    private Integer schoolYear;

    public Student() {
    }

    public Student(String firstName, String middleName, String lastName, LocalDate dateOfBirth, House house, boolean isPrefect, int enrollmentYear, boolean isGraduated, int schoolYear) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.house = house;
        this.isPrefect = isPrefect;
        this.enrollmentYear = enrollmentYear;
        this.isGraduated = isGraduated;
        this.schoolYear = schoolYear;
    }

    public Student(String firstName, String lastName, LocalDate dateOfBirth, House house, boolean isPrefect, int enrollmentYear, boolean isGraduated, int schoolYear) {
        this(firstName, null, lastName, dateOfBirth, house, isPrefect, enrollmentYear, isGraduated, schoolYear);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public House getHouse() {
        return house;
    }

    public void setHouse(House house) {
        this.house = house;
    }

    public boolean isPrefect() {
        return isPrefect;
    }

    public void setPrefect(boolean prefect) {
        isPrefect = prefect;
    }

    public int getEnrollmentYear() {
        return enrollmentYear;
    }

    public void setEnrollmentYear(int enrollmentYear) {
        this.enrollmentYear = enrollmentYear;
    }

    public int getGraduationYear() {
        return graduationYear;
    }

    public void setGraduationYear(int graduationYear) {
        this.graduationYear = graduationYear;
    }

    public boolean isGraduated() {
        return isGraduated;
    }

    public void setGraduated(boolean graduated) {
        isGraduated = graduated;
    }

    public int getSchoolYear() {
        return schoolYear;
    }

    public void setSchoolYear(int schoolYear) {
        this.schoolYear = schoolYear;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", house=" + house +
                ", isPrefect=" + isPrefect +
                ", enrollmentYear=" + enrollmentYear +
                ", graduationYear=" + graduationYear +
                ", isGraduated=" + isGraduated +
                ", schoolYear=" + schoolYear +
                '}';
    }
}
