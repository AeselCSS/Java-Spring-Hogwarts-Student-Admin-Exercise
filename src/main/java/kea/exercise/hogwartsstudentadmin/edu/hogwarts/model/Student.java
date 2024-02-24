package kea.exercise.hogwartsstudentadmin.edu.hogwarts.model;

import jakarta.persistence.*;

import java.time.LocalDate;

import static kea.exercise.hogwartsstudentadmin.edu.hogwarts.utility.StringUtility.*;

@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String middleName;
    private String lastName;
    private LocalDate dateOfBirth;
    @JoinColumn(name = "house")
    private @ManyToOne(fetch = FetchType.EAGER) House house;
    private boolean isPrefect;
    private Integer enrollmentYear;
    private Integer graduationYear;
    private boolean isGraduated = false;
    private Integer schoolYear;

    public Student() {
    }

    public Student(String firstName, String middleName, String lastName, LocalDate dateOfBirth, House house, Boolean isPrefect, Integer enrollmentYear, Integer graduationYear, Boolean isGraduated, Integer schoolYear) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.house = house;
        this.isPrefect = isPrefect;
        this.enrollmentYear = enrollmentYear;
        this.graduationYear = graduationYear;
        this.isGraduated = isGraduated;
        this.schoolYear = schoolYear;
    }

    public Student(String firstName, String middleName, String lastName, LocalDate dateOfBirth, House house, Boolean isPrefect, Integer enrollmentYear, Boolean isGraduated,  Integer schoolYear) {
       this(firstName, middleName, lastName, dateOfBirth, house, isPrefect, enrollmentYear, null, isGraduated, schoolYear);
    }

    public Student(String firstName, String lastName, LocalDate dateOfBirth, House house, Boolean isPrefect, Integer enrollmentYear, Boolean isGraduated,  Integer schoolYear) {
        this(firstName, null, lastName, dateOfBirth, house, isPrefect, enrollmentYear, null, isGraduated,  schoolYear);
    }

    public Student(String fullName, LocalDate dateOfBirth, House house, Boolean isPrefect, Integer enrollmentYear, Boolean isGraduated, Integer schoolYear) {
        setName(fullName);
        this.dateOfBirth = dateOfBirth;
        this.house = house;
        this.isPrefect = isPrefect;
        this.enrollmentYear = enrollmentYear;
        this.isGraduated = isGraduated;
        this.schoolYear = schoolYear;
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

    public Integer getEnrollmentYear() {
        return enrollmentYear;
    }

    public void setEnrollmentYear(Integer enrollmentYear) {
        this.enrollmentYear = enrollmentYear;
    }

    public Integer getGraduationYear() {
        return graduationYear;
    }

    public void setGraduationYear(Integer graduationYear) {
        this.graduationYear = graduationYear;
        // if graduation year is set, update isGraduated to true.
        this.isGraduated = graduationYear != null;
    }

    public Boolean isGraduated() {
        return isGraduated;
    }

    public Boolean getGraduated() {
        return isGraduated;
    }

    public void setGraduated(Boolean graduated) {
        isGraduated = graduated;
    }

    public Integer getSchoolYear() {
        return schoolYear;
    }

    public void setSchoolYear(Integer schoolYear) {
        this.schoolYear = schoolYear;
    }

    public String getName() {
        return firstMiddleLastToFullName(firstName, middleName, lastName);
    }

    public void setName(String fullName) {
        String[] nameParts = fullNameAsFirstMiddleLast(fullName);
        this.firstName = nameParts[0];
        this.middleName = nameParts[1];
        this.lastName = nameParts[2];
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



