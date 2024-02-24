package kea.exercise.hogwartsstudentadmin.edu.hogwarts.dto;

import java.time.LocalDate;

import static kea.exercise.hogwartsstudentadmin.edu.hogwarts.utility.StringUtility.*;

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
) { public TeacherRequestDTO {
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
