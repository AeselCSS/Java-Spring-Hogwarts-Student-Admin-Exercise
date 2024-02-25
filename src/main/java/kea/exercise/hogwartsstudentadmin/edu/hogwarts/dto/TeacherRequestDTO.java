package kea.exercise.hogwartsstudentadmin.edu.hogwarts.dto;

import java.time.LocalDate;

import static kea.exercise.hogwartsstudentadmin.edu.hogwarts.utility.StringUtility.*;

/**
 * Data Transfer Object for TeacherRequest
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
    /**
     * Constructor for TeacherRequestDTO
     * Capitalizes the house and sets the first, middle, and last name
     * @param id
     * @param firstName
     * @param middleName
     * @param lastName
     * @param name
     * @param dateOfBirth
     * @param house
     * @param isHeadOfHouse
     * @param employment
     * @param employmentStart
     * @param employmentEnd
     */
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
