package kea.exercise.hogwartsstudentadmin.edu.hogwarts.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

import static kea.exercise.hogwartsstudentadmin.edu.hogwarts.utility.StringUtility.toFullName;
import static kea.exercise.hogwartsstudentadmin.edu.hogwarts.utility.StringUtility.toNameParts;

/**
 * The Teacher class is a model that represents a teacher in the Hogwarts Student Admin system.
 * It contains information about the first name, middle name, last name, date of birth, house, head of house status, employment status, and employment dates of the teacher.
 */
@Entity
public class Teacher {
    /**
     * The id of the teacher.
     * It is a unique identifier for the teacher and is generated automatically by the database.
     * It is the primary key of the teacher entity.
     * It is of type Long.
     * It is not nullable.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The first name of the teacher.
     * It is a string that represents the first name of the teacher.
     * It is not nullable.
     */
    @NotBlank(message = "First name is mandatory")
    private String firstName;
    private String middleName;

    /**
     * The last name of the teacher.
     * It is a string that represents the last name of the teacher.
     * It is not nullable.
     */
    @NotBlank(message = "Last name is mandatory")
    private String lastName;

    /**
     * The date of birth of the teacher.
     * It is a LocalDate object that represents the date of birth of the teacher.
     * It is not nullable.
     * It must be in the past.
     */
    @Past(message = "Date of birth must be in the past")
    private LocalDate dateOfBirth;

    /**
     * The house of the teacher.
     * It is a House object that represents the house of the teacher.
     * It is a many-to-one relationship with the House model.
     * It is fetched eagerly.
     * It is not nullable.
     */
    @NotNull(message = "House is mandatory")
    @JoinColumn(name = "house")
    private @ManyToOne(fetch = FetchType.EAGER) House house;

    /**
     * The head of house status of the teacher.
     * It is a boolean that represents whether the teacher is the head of house or not.
     * It is not nullable.
     */
    @NotNull(message = "Head of house status is mandatory")
    private boolean isHeadOfHouse;

    /**
     * The employment status of the teacher.
     * It is an enumeration that represents the employment status of the teacher.
     * It is not nullable.
     */
    private @Enumerated(EnumType.STRING) EmpType employment;

    /**
     * The employment start date of the teacher.
     * It is a LocalDate object that represents the employment start date of the teacher.
     * It is not nullable.
     * It must be in the past.
     */
    @NotNull(message = "Employment start date is mandatory")
    private LocalDate employmentStart;

    /**
     * The employment end date of the teacher.
     * It is a LocalDate object that represents the employment end date of the teacher.
     * It is nullable.
     * It must be in the future.
     */
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

    public String getFullName() {
        return toFullName(firstName, middleName, lastName);
    }

    public void setFullName(String fullName) {
        String[] nameParts = toNameParts(fullName);
        this.firstName = nameParts[0];
        this.middleName = nameParts[1];
        this.lastName = nameParts[2];
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
