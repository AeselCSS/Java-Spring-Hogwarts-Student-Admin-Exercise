package kea.exercise.hogwartsstudentadmin.edu.hogwarts.dto;

import java.time.LocalDate;

/**
 * Data Transfer Object for TeacherResponse
 * <p>
 *     This record is used to transfer data related to a teacher response.
 *     It includes information about the teacher such as the teacher id, name, date of birth, house, head of house status, employment, employment start, and employment end.
 *
 *     @param id The unique identifier for the teacher.
 *     @param name The full name of the teacher. This is a String that represents the full name of the teacher.
 *     @param dateOfBirth The date of birth of the teacher. This is a LocalDate object that represents the date of birth of the teacher.
 *     @param house The house of the teacher. This is a String that represents the house of the teacher.
 *     @param isHeadOfHouse The head of house status of the teacher. This is a Boolean that represents whether the teacher is a head of house or not.
 *     @param employment The employment status of the teacher. This is a String that represents the employment status of the teacher.
 *     @param employmentStart The employment start date of the teacher. This is a LocalDate object that represents the employment start date of the teacher.
 *     @param employmentEnd The employment end date of the teacher. This is a LocalDate object that represents the employment end date of the teacher.
 */
public record TeacherResponseDTO(
        Long id,
        String name,
        LocalDate dateOfBirth,
        String house,
        Boolean isHeadOfHouse,
        String employment,
        LocalDate employmentStart,
        LocalDate employmentEnd
) {

}
