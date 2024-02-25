package kea.exercise.hogwartsstudentadmin.edu.hogwarts.dto;

import java.time.LocalDate;

import static kea.exercise.hogwartsstudentadmin.edu.hogwarts.utility.StringUtility.toNameParts;
import static kea.exercise.hogwartsstudentadmin.edu.hogwarts.utility.StringUtility.toTitleCase;

/**
 * Data Transfer Object for StudentRequest
 * <p>
 *     This record is used to transfer data related to a student request.
 *     It includes information about the student such as the student id, first name, middle name, last name, name, date of birth, house, prefect status, enrollment year, graduation year, graduation status, and school year.
 *
 *     @param id The unique identifier for the student.
 *     @param firstName The first name of the student. This is a String that represents the first name of the student.
 *     @param middleName The middle name of the student. This is a String that represents the middle name of the student.
 *     @param lastName The last name of the student. This is a String that represents the last name of the student.
 *     @param name The full name of the student. This is a String that represents the full name of the student.
 *     @param dateOfBirth The date of birth of the student. This is a LocalDate object that represents the date of birth of the student.
 *     @param house The house of the student. This is a String that represents the house of the student.
 *     @param isPrefect The prefect status of the student. This is a Boolean that represents whether the student is a prefect or not.
 *     @param enrollmentYear The enrollment year of the student. This is an Integer that represents the enrollment year of the student.
 *     @param graduationYear The graduation year of the student. This is an Integer that represents the graduation year of the student.
 *     @param isGraduated The graduation status of the student. This is a Boolean that represents whether the student has graduated or not.
 *     @param schoolYear The school year of the student. This is an Integer that represents the school year of the student.
 */
public record StudentRequestDTO(
        Long id,
        String firstName,
        String middleName,
        String lastName,
        String name,
        LocalDate dateOfBirth,
        String house,
        Boolean isPrefect,
        Integer enrollmentYear,
        Integer graduationYear,
        Boolean isGraduated,
        Integer schoolYear

) {
    /**
     * This constructor initializes a new instance of the StudentRequestDTO class.
     * It takes a student id, first name, middle name, last name, name, date of birth, house, prefect status, enrollment year, graduation year, graduation status, and school year as parameters.
     * It sets the corresponding fields of the class to the values of the parameters.
     * It also performs some data validation and transformation to ensure that the data is in the correct format.
     * @param id The unique identifier for the student.
     * @param firstName The first name of the student. This is a String that represents the first name of the student.
     * @param middleName The middle name of the student. This is a String that represents the middle name of the student.
     * @param lastName The last name of the student. This is a String that represents the last name of the student.
     * @param name The full name of the student. This is a String that represents the full name of the student.
     * @param dateOfBirth The date of birth of the student. This is a LocalDate object that represents the date of birth of the student.
     * @param house The house of the student. This is a String that represents the house of the student.
     * @param isPrefect The prefect status of the student. This is a Boolean that represents whether the student is a prefect or not.
     * @param enrollmentYear The enrollment year of the student. This is an Integer that represents the enrollment year of the student.
     * @param graduationYear The graduation year of the student. This is an Integer that represents the graduation year of the student.
     * @param isGraduated The graduation status of the student. This is a Boolean that represents whether the student has graduated or not.
     * @param schoolYear The school year of the student. This is an Integer that represents the school year of the student.
     */
    public StudentRequestDTO {
    if (name != null) {
        String[] names = toNameParts(name);
        firstName = names[0];
        middleName = names[1];
        lastName = names[2];
    }

    if (house != null) {
        house = toTitleCase(house);
    }

    if (graduationYear != null) {
        isGraduated = true;
    }
}
}
