package kea.exercise.hogwartsstudentadmin.edu.hogwarts.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDate;
import java.time.Period;

import static kea.exercise.hogwartsstudentadmin.edu.hogwarts.utility.StringUtility.toTitleCase;

public class TeacherDTO {
    private String name;
    @JsonIgnore
    private LocalDate dateOfBirth;
    private int age;
    private String house;
    private boolean isHeadOfHouse;
    private String employment;
    private LocalDate employmentStart;
    private LocalDate employmentEnd;

    public TeacherDTO() {
    }

    public TeacherDTO(String firstName, String middleName, String lastName, LocalDate dateOfBirth, String house, boolean isHeadOfHouse, String employment, LocalDate employmentStart, LocalDate employmentEnd) {
        setName(firstName, middleName, lastName);
        this.dateOfBirth = dateOfBirth;
        setAge(dateOfBirth);
        this.house = house;
        this.isHeadOfHouse = isHeadOfHouse;
        this.employment = employment;
        this.employmentStart = employmentStart;
        this.employmentEnd = employmentEnd;
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

    public boolean isHeadOfHouse() {
        return isHeadOfHouse;
    }

    public void setHeadOfHouse(boolean headOfHouse) {
        isHeadOfHouse = headOfHouse;
    }

    public String getEmployment() {
        return employment;
    }

    public void setEmployment(String employment) {
        this.employment = employment;
    }

    public LocalDate getEmploymentStart() {
        return employmentStart;
    }

    public void setEmploymentStart(LocalDate employmentStart) {
        this.employmentStart = employmentStart;
    }

    public LocalDate getEmploymentEnd() {
        return employmentEnd;
    }

    public void setEmploymentEnd(LocalDate employmentEnd) {
        this.employmentEnd = employmentEnd;
    }
}
