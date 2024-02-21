package kea.exercise.hogwartsstudentadmin.edu.hogwarts.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDate;
import java.time.Period;

import static kea.exercise.hogwartsstudentadmin.edu.hogwarts.utility.StringUtility.toTitleCase;

public class StudentDTO {
    private String name;
    @JsonIgnore
    private LocalDate dateOfBirth;
    private int age;
    private String house;
    private boolean isPrefect;
    private int enrollmentYear;
    private int graduationYear;
    private boolean isGraduated;
    private int schoolYear;

    public StudentDTO() {
    }

    public StudentDTO(String firstName, String middleName, String lastName, LocalDate dateOfBirth, String house, boolean isPrefect, int enrollmentYear, boolean isGraduated, int schoolYear) {
        setName(firstName, middleName, lastName);
        this.dateOfBirth = dateOfBirth;
        setAge(dateOfBirth);
        this.house = house;
        this.isPrefect = isPrefect;
        this.enrollmentYear = enrollmentYear;
        setGraduationYear(enrollmentYear);
        this.isGraduated = isGraduated;
        this.schoolYear = schoolYear;
    }

    public String getName() {
        return name;
    }

    public void setName(String firstName, String middleName, String lastName) {
        if (middleName == null || middleName.isEmpty()) {
            this.name = toTitleCase(firstName, lastName);
        } else {
            this.name = toTitleCase(firstName, middleName, lastName);
        }
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public int getAge() {
        return age;
    }

    public void setAge(LocalDate dateOfBirth) {
        this.age = Period.between(dateOfBirth, LocalDate.now().withYear(1992)).getYears();
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
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

    public void setGraduationYear(int enrollmentYear) {
        this.graduationYear = enrollmentYear + 7;
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
}
