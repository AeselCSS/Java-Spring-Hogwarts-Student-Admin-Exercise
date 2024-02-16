package kea.exercise.hogwartsstudentadmin.edu.hogwarts.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

@Entity
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "First name is mandatory")
    private String firstName;
    private String middleName;

    @NotBlank(message = "Last name is mandatory")
    private String lastName;

    @Past(message = "Date of birth must be in the past")
    private LocalDate dateOfBirth;

    @NotNull(message = "House is mandatory")
    private @ManyToOne(fetch = FetchType.EAGER) House house;

    @NotNull(message = "Head of house status is mandatory")
    private boolean isHeadOfHouse;


    private @Enumerated(EnumType.STRING) EmpType employment;

    @NotNull(message = "Employment start date is mandatory")
    private LocalDate employmentStart;
    private LocalDate employmentEnd;

    public Teacher() {
    }

    public Teacher(String firstName, String middleName, String lastName, LocalDate dateOfBirth, House house, boolean isHeadOfHouse, EmpType employment, LocalDate employmentStart, LocalDate employmentEnd) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.house = house;
        this.isHeadOfHouse = isHeadOfHouse;
        this.employment = employment;
        this.employmentStart = employmentStart;
        this.employmentEnd = employmentEnd;
    }

    public Teacher(String firstName, String lastName, LocalDate dateOfBirth, House house, boolean isHeadOfHouse, EmpType employment, LocalDate employmentStart, LocalDate employmentEnd) {
        this(firstName, null, lastName, dateOfBirth, house, isHeadOfHouse, employment, employmentStart, employmentEnd);
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

    public boolean isHeadOfHouse() {
        return isHeadOfHouse;
    }

    public void setHeadOfHouse(boolean headOfHouse) {
        isHeadOfHouse = headOfHouse;
    }

    public EmpType getEmployment() {
        return employment;
    }

    public void setEmployment(EmpType employment) {
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

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", house=" + house +
                ", isHeadOfHouse=" + isHeadOfHouse +
                ", employment=" + employment +
                ", employmentStart=" + employmentStart +
                ", employmentEnd=" + employmentEnd +
                '}';
    }
}
