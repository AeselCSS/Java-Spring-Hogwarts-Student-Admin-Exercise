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

    /**
     * The middle name of the teacher.
     * It is a string that represents the middle name of the teacher.
     */
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

    /**
     * Default constructor for the Teacher class.
     */
    public Teacher() {
    }

    /**
     * Constructor for the Teacher class with all fields.
     * @param firstName The first name of the teacher.
     * @param middleName The middle name of the teacher.
     * @param lastName The last name of the teacher.
     * @param dateOfBirth The date of birth of the teacher.
     * @param house The house of the teacher.
     * @param isHeadOfHouse The head of house status of the teacher.
     * @param employment The employment status of the teacher.
     * @param employmentStart The employment start date of the teacher.
     * @param employmentEnd The employment end date of the teacher.
     */
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

    /**
     * Constructor for the Teacher class with all fields except middleName.
     * @param firstName The first name of the teacher.
     * @param lastName The last name of the teacher.
     * @param dateOfBirth The date of birth of the teacher.
     * @param house The house of the teacher.
     * @param isHeadOfHouse The head of house status of the teacher.
     * @param employment The employment status of the teacher.
     * @param employmentStart The employment start date of the teacher.
     * @param employmentEnd The employment end date of the teacher.
     */
    public Teacher(String firstName, String lastName, LocalDate dateOfBirth, House house, boolean isHeadOfHouse, EmpType employment, LocalDate employmentStart, LocalDate employmentEnd) {
        this(firstName, null, lastName, dateOfBirth, house, isHeadOfHouse, employment, employmentStart, employmentEnd);
    }

    /**
     * Getter for the id of the teacher.
     * @return The id of the teacher.
     */
    public Long getId() {
        return id;
    }

    /**
     * Setter for the id of the teacher.
     * @param id The new id of the teacher.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Getter for the first name of the teacher.
     * @return The first name of the teacher.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Setter for the first name of the teacher.
     * @param firstName The new first name of the teacher.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Getter for the middle name of the teacher.
     * @return The middle name of the teacher.
     */
    public String getMiddleName() {
        return middleName;
    }

    /**
     * Setter for the middle name of the teacher.
     * @param middleName The new middle name of the teacher.
     */
    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    /**
     * Getter for the last name of the teacher.
     * @return The last name of the teacher.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Setter for the last name of the teacher.
     * @param lastName The new last name of the teacher.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Getter for the date of birth of the teacher.
     * @return The date of birth of the teacher.
     */
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * Setter for the date of birth of the teacher.
     * @param dateOfBirth The new date of birth of the teacher.
     */
    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * Getter for the house of the teacher.
     * @return The house of the teacher.
     */
    public House getHouse() {
        return house;
    }

    /**
     * Setter for the house of the teacher.
     * @param house The new house of the teacher.
     */
    public void setHouse(House house) {
        this.house = house;
    }

    /**
     * Getter for the head of house status of the teacher.
     * @return The head of house status of the teacher.
     */
    public boolean isHeadOfHouse() {
        return isHeadOfHouse;
    }

    /**
     * Setter for the head of house status of the teacher.
     * @param headOfHouse The new head of house status of the teacher.
     */
    public void setHeadOfHouse(boolean headOfHouse) {
        isHeadOfHouse = headOfHouse;
    }

    /**
     * Getter for the employment status of the teacher.
     * @return The employment status of the teacher.
     */
    public EmpType getEmployment() {
        return employment;
    }

    /**
     * Setter for the employment status of the teacher.
     * @param employment The new employment status of the teacher.
     */
    public void setEmployment(EmpType employment) {
        this.employment = employment;
    }

    /**
     * Getter for the employment start date of the teacher.
     * @return The employment start date of the teacher.
     */
    public LocalDate getEmploymentStart() {
        return employmentStart;
    }

    /**
     * Setter for the employment start date of the teacher.
     * @param employmentStart The new employment start date of the teacher.
     */
    public void setEmploymentStart(LocalDate employmentStart) {
        this.employmentStart = employmentStart;
    }

    /**
     * Getter for the employment end date of the teacher.
     * @return The employment end date of the teacher.
     */
    public LocalDate getEmploymentEnd() {
        return employmentEnd;
    }

    /**
     * Setter for the employment end date of the teacher.
     * @param employmentEnd The new employment end date of the teacher.
     */
    public void setEmploymentEnd(LocalDate employmentEnd) {
        this.employmentEnd = employmentEnd;
    }

    /**
     * Getter for the full name of the teacher.
     * @return The full name of the teacher.
     */
    public String getFullName() {
        return toFullName(firstName, middleName, lastName);
    }

    /**
     * Setter for the full name of the teacher.
     * Splits the full name into first name, middle name, and last name.
     * @param fullName The new full name of the teacher.
     */
    public void setFullName(String fullName) {
        String[] nameParts = toNameParts(fullName);
        this.firstName = nameParts[0];
        this.middleName = nameParts[1];
        this.lastName = nameParts[2];
    }

    /**
     * Returns a string representation of the teacher.
     * @return A string representation of the teacher.
     */
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