package kea.exercise.hogwartsstudentadmin.edu.hogwarts.model;

import jakarta.persistence.*;

import java.time.LocalDate;

import static kea.exercise.hogwartsstudentadmin.edu.hogwarts.utility.StringUtility.*;

/**
 * The Student class is a model that represents a student in the Hogwarts Student Admin system.
 * It contains information about the name, date of birth, house, prefect status, enrollment year, graduation year, and school year of the student.
 */
@Entity
public class Student {
    /**
     * The id of the student.
     * It is a unique identifier for the student and is generated automatically by the database.
     * It is the primary key of the student entity.
     * It is of type Long.
     * It is not nullable.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The first name of the student.
     * It is a string that represents the first name of the student.
     * It is not nullable.
     */
    private String firstName;

    /**
     * The middle name of the student.
     * It is a string that represents the middle name of the student.
     */
    private String middleName;

    /**
     * The last name of the student.
     * It is a string that represents the last name of the student.
     * It is not nullable.
     */
    private String lastName;

    /**
     * The date of birth of the student.
     * It is a LocalDate object that represents the date of birth of the student.
     * It is not nullable.
     */
    private LocalDate dateOfBirth;

    /**
     * The house of the student.
     * It is a House object that represents the house of the student.
     * It is a many-to-one relationship with the House model.
     * It is fetched eagerly.
     */
    @JoinColumn(name = "house")
    private @ManyToOne(fetch = FetchType.EAGER) House house;

    /**
     * The prefect status of the student.
     * It is a boolean that represents whether the student is a prefect or not.
     */
    private boolean isPrefect;

    /**
     * The enrollment year of the student.
     * It is an integer that represents the enrollment year of the student.
     */
    private Integer enrollmentYear;

    /**
     * The graduation year of the student.
     * It is an integer that represents the graduation year of the student.
     */
    private Integer graduationYear;

    /**
     * The graduated status of the student.
     * It is a boolean that represents whether the student has graduated or not.
     */
    private boolean isGraduated = false;

     /**
     * The school year of the student.
     * It is an integer that represents the school year of the student.
     */
    private Integer schoolYear;

    /**
     * Default constructor for the Student class.
     */
    public Student() {
    }

    /**
     * Constructor for the Student class with all fields.
     * @param firstName The first name of the student.
     * @param middleName The middle name of the student.
     * @param lastName The last name of the student.
     * @param dateOfBirth The date of birth of the student.
     * @param house The house of the student.
     * @param isPrefect The prefect status of the student.
     * @param enrollmentYear The enrollment year of the student.
     * @param graduationYear The graduation year of the student.
     * @param isGraduated The graduated status of the student.
     * @param schoolYear The school year of the student.
     */
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

    /**
     * Constructor for the Student class with all fields except graduationYear.
     * @param firstName The first name of the student.
     * @param middleName The middle name of the student.
     * @param lastName The last name of the student.
     * @param dateOfBirth The date of birth of the student.
     * @param house The house of the student.
     * @param isPrefect The prefect status of the student.
     * @param enrollmentYear The enrollment year of the student.
     * @param isGraduated The graduated status of the student.
     * @param schoolYear The school year of the student.
     */
    public Student(String firstName, String middleName, String lastName, LocalDate dateOfBirth, House house, Boolean isPrefect, Integer enrollmentYear, Boolean isGraduated,  Integer schoolYear) {
       this(firstName, middleName, lastName, dateOfBirth, house, isPrefect, enrollmentYear, null, isGraduated, schoolYear);
    }

    /**
     * Constructor for the Student class with all fields except middleName and graduationYear.
     * @param firstName The first name of the student.
     * @param lastName The last name of the student.
     * @param dateOfBirth The date of birth of the student.
     * @param house The house of the student.
     * @param isPrefect The prefect status of the student.
     * @param enrollmentYear The enrollment year of the student.
     * @param isGraduated The graduated status of the student.
     * @param schoolYear The school year of the student.
     */
    public Student(String firstName, String lastName, LocalDate dateOfBirth, House house, Boolean isPrefect, Integer enrollmentYear, Boolean isGraduated,  Integer schoolYear) {
        this(firstName, null, lastName, dateOfBirth, house, isPrefect, enrollmentYear, null, isGraduated,  schoolYear);
    }

    /**
     * Constructor for the Student class with all fields except middleName, lastName and graduationYear.
     * @param fullName The full name of the student.
     * @param dateOfBirth The date of birth of the student.
     * @param house The house of the student.
     * @param isPrefect The prefect status of the student.
     * @param enrollmentYear The enrollment year of the student.
     * @param isGraduated The graduated status of the student.
     * @param schoolYear The school year of the student.
     */
    public Student(String fullName, LocalDate dateOfBirth, House house, Boolean isPrefect, Integer enrollmentYear, Boolean isGraduated, Integer schoolYear) {
        setName(fullName);
        this.dateOfBirth = dateOfBirth;
        this.house = house;
        this.isPrefect = isPrefect;
        this.enrollmentYear = enrollmentYear;
        this.isGraduated = isGraduated;
        this.schoolYear = schoolYear;
    }

    /**
     * Getter for the id of the student.
     * @return The id of the student.
     */
    public Long getId() {
        return id;
    }

    /**
     * Setter for the id of the student.
     * @param id The new id of the student.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Getter for the first name of the student.
     * @return The first name of the student.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Setter for the first name of the student.
     * @param firstName The new first name of the student.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Getter for the middle name of the student.
     * @return The middle name of the student.
     */
    public String getMiddleName() {
        return middleName;
    }

    /**
     * Setter for the middle name of the student.
     * @param middleName The new middle name of the student.
     */
    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    /**
     * Getter for the last name of the student.
     * @return The last name of the student.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Setter for the last name of the student.
     * @param lastName The new last name of the student.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Getter for the date of birth of the student.
     * @return The date of birth of the student.
     */
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * Setter for the date of birth of the student.
     * @param dateOfBirth The new date of birth of the student.
     */
    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * Getter for the house of the student.
     * @return The house of the student.
     */
    public House getHouse() {
        return house;
    }

    /**
     * Setter for the house of the student.
     * @param house The new house of the student.
     */
    public void setHouse(House house) {
        this.house = house;
    }

    /**
     * Getter for the prefect status of the student.
     * @return The prefect status of the student.
     */
    public boolean isPrefect() {
        return isPrefect;
    }

    /**
     * Setter for the prefect status of the student.
     * @param prefect The new prefect status of the student.
     */
    public void setPrefect(boolean prefect) {
        isPrefect = prefect;
    }

    /**
     * Getter for the enrollment year of the student.
     * @return The enrollment year of the student.
     */
    public Integer getEnrollmentYear() {
        return enrollmentYear;
    }

    /**
     * Setter for the enrollment year of the student.
     * @param enrollmentYear The new enrollment year of the student.
     */
    public void setEnrollmentYear(Integer enrollmentYear) {
        this.enrollmentYear = enrollmentYear;
    }

    /**
     * Getter for the graduation year of the student.
     * @return The graduation year of the student.
     */
    public Integer getGraduationYear() {
        return graduationYear;
    }

    /**
     * Setter for the graduation year of the student.
     * Also updates the graduated status of the student to true if a graduation year is set.
     * @param graduationYear The new graduation year of the student.
     */
    public void setGraduationYear(Integer graduationYear) {
        this.graduationYear = graduationYear;
        // if graduation year is set, update isGraduated to true.
        this.isGraduated = graduationYear != null;
    }

    /**
     * Getter for the graduated status of the student.
     * @return The graduated status of the student.
     */
    public Boolean isGraduated() {
        return isGraduated;
    }

    /**
     * Getter for the graduated status of the student.
     * @return The graduated status of the student.
     */
    public Boolean getGraduated() {
        return isGraduated;
    }

    /**
     * Setter for the graduated status of the student.
     * @param graduated The new graduated status of the student.
     */
    public void setGraduated(Boolean graduated) {
        isGraduated = graduated;
    }

    /**
     * Getter for the school year of the student.
     * @return The school year of the student.
     */
    public Integer getSchoolYear() {
        return schoolYear;
    }

    /**
     * Setter for the school year of the student.
     * @param schoolYear The new school year of the student.
     */
    public void setSchoolYear(Integer schoolYear) {
        this.schoolYear = schoolYear;
    }

    /**
     * Getter for the full name of the student.
     * @return The full name of the student.
     */
    public String getName() {
        return toFullName(firstName, middleName, lastName);
    }

    /**
     * Setter for the full name of the student.
     * Splits the full name into first name, middle name, and last name.
     * @param fullName The new full name of the student.
     */
    public void setName(String fullName) {
        String[] nameParts = toNameParts(fullName);
        this.firstName = nameParts[0];
        this.middleName = nameParts[1];
        this.lastName = nameParts[2];
    }

    /**
     * Returns a string representation of the student.
     * @return A string representation of the student.
     */
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