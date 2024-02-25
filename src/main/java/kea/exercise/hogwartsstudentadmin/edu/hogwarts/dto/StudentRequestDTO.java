package kea.exercise.hogwartsstudentadmin.edu.hogwarts.dto;

import java.time.LocalDate;

import static kea.exercise.hogwartsstudentadmin.edu.hogwarts.utility.StringUtility.toNameParts;
import static kea.exercise.hogwartsstudentadmin.edu.hogwarts.utility.StringUtility.toTitleCase;

/**
 * Data Transfer Object for StudentRequest
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
     * Constructor for StudentRequestDTO
     * Capitalizes the house and sets the first, middle, and last name
     * @param id
     * @param firstName
     * @param middleName
     * @param lastName
     * @param name
     * @param dateOfBirth
     * @param house
     * @param isPrefect
     * @param enrollmentYear
     * @param graduationYear
     * @param isGraduated
     * @param schoolYear
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
