package kea.exercise.hogwartsstudentadmin.edu.hogwarts.dto;

import java.time.LocalDate;

public record StudentResponseDTO(
        Long id,
        String name,
        LocalDate dateOfBirth,
        String house,
        Boolean isPrefect,
        Integer enrollmentYear,
        Integer graduationYear,
        Boolean isGraduated,
        Integer schoolYear
) {

}
