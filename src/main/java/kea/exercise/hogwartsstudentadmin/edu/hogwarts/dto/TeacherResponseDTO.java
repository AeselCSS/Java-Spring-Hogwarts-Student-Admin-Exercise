package kea.exercise.hogwartsstudentadmin.edu.hogwarts.dto;

import java.time.LocalDate;

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
