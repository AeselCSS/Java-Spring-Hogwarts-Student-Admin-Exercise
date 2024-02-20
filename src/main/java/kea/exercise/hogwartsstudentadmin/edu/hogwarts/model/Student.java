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
    private @ManyToOne(fetch = FetchType.EAGER) House house;

    @NotNull(message = "Prefect status is mandatory")
    private boolean isPrefect;

    @NotNull(message = "Enrollment year is mandatory")
    @Min(value = 1900, message = "Enrollment year must be after 1900")
    private int enrollmentYear;

    @NotNull(message = "Graduation year is mandatory")
    @Min(value = 1900, message = "Graduation year must be after 1900")
    private int graduationYear;

    @NotNull(message = "Graduated status is mandatory")
    private boolean isGraduated;

    public Student() {
    }

    public Student(String firstName, String middleName, String lastName, LocalDate dateOfBirth, House house, boolean isPrefect, int enrollmentYear, int graduationYear, boolean isGraduated) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.house = house;
        this.isPrefect = isPrefect;
        this.enrollmentYear = enrollmentYear;
        this.graduationYear = graduationYear;
        this.isGraduated = isGraduated;
    }

    public Student(String firstName, String lastName, LocalDate dateOfBirth, House house, boolean isPrefect, int enrollmentYear, int graduationYear, boolean isGraduated) {
        this(firstName, null, lastName, dateOfBirth, house, isPrefect, enrollmentYear, graduationYear, isGraduated);
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
                '}';
    }
}
