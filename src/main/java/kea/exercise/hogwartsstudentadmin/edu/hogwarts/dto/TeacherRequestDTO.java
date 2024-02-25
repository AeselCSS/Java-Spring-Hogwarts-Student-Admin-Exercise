package kea.exercise.hogwartsstudentadmin.edu.hogwarts.dto;

import java.time.LocalDate;

import static kea.exercise.hogwartsstudentadmin.edu.hogwarts.utility.StringUtility.*;

/**
 * Data Transfer Object for TeacherRequest
 * <p>
 *     This record is used to transfer data related to a teacher request.
 *     It includes information about the teacher such as the teacher id, first name, middle name, last name, name, date of birth, house, head of house status, employment, employment start, and employment end.
 *
 *     @param id The unique identifier for the teacher.
 *     @param firstName The first name of the teacher. This is a String that represents the first name of the teacher.
 *     @param middleName The middle name of the teacher. This is a String that represents the middle name of the teacher.
 *     @param lastName The last name of the teacher. This is a String that represents the last name of the teacher.
 *     @param name The full name of the teacher. This is a String that represents the full name of the teacher.
 *     @param dateOfBirth The date of birth of the teacher. This is a LocalDate object that represents the date of birth of the teacher.
 *     @param house The house of the teacher. This is a String that represents the house of the teacher.
 *     @param isHeadOfHouse The head of house status of the teacher. This is a Boolean that represents whether the teacher is a head of house or not.
 *     @param employment The employment status of the teacher. This is a String that represents the employment status of the teacher.
 *     @param employmentStart The employment start date of the teacher. This is a LocalDate object that represents the employment start date of the teacher.
 *     @param employmentEnd The employment end date of the teacher. This is a LocalDate object that represents the employment end date of the teacher.
 */
public record TeacherRequestDTO(
        Long id,
        String firstName,
        String middleName,
        String lastName,
        String name,
        LocalDate dateOfBirth,
        String house,
        Boolean isHeadOfHouse,
        String employment,
        LocalDate employmentStart,
        LocalDate employmentEnd
) {
    public TeacherRequestDTO {
    if (name != null) {
        String[] names = toNameParts(name);
        firstName = names[0];
        middleName = names[1];
        lastName = names[2];
    }

    if (house != null) {
        house = toTitleCase(house);
    }

    if (employment != null) {
        employment = toTitleCase(employment);
    }
}
}
