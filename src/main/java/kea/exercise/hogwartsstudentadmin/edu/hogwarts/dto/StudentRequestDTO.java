package kea.exercise.hogwartsstudentadmin.edu.hogwarts.dto;

import java.time.LocalDate;

import static kea.exercise.hogwartsstudentadmin.edu.hogwarts.utility.StringUtility.toNameParts;
import static kea.exercise.hogwartsstudentadmin.edu.hogwarts.utility.StringUtility.toTitleCase;

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

) { public StudentRequestDTO {
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
