package kea.exercise.hogwartsstudentadmin.edu.hogwarts.dto;

import java.time.LocalDate;

import static kea.exercise.hogwartsstudentadmin.edu.hogwarts.utility.StringUtility.fullNameAsFirstMiddleLast;

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
        String[] names = fullNameAsFirstMiddleLast(name);
        firstName = names[0];
        middleName = names[1];
        lastName = names[2];
    }
    if (house != null) {

    }
    }
}
