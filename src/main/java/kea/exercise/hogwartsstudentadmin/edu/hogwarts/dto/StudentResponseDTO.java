package kea.exercise.hogwartsstudentadmin.edu.hogwarts.dto;

import java.time.LocalDate;

/**
 * Data Transfer Object for StudentResponse
 * <p>
 *     This record is used to transfer data related to a student response.
 *     It includes information about the student such as the student id, name, date of birth, house, prefect status, enrollment year, graduation year, graduation status, and school year.
 *
 *     @param id The unique identifier for the student.
 *     @param name The full name of the student. This is a String that represents the full name of the student.
 *     @param dateOfBirth The date of birth of the student. This is a LocalDate object that represents the date of birth of the student.
 *     @param house The house of the student. This is a String that represents the house of the student.
 *     @param isPrefect The prefect status of the student. This is a Boolean that represents whether the student is a prefect or not.
 *     @param enrollmentYear The enrollment year of the student. This is an Integer that represents the enrollment year of the student.
 *     @param graduationYear The graduation year of the student. This is an Integer that represents the graduation year of the student.
 *     @param isGraduated The graduation status of the student. This is a Boolean that represents whether the student has graduated or not.
 *     @param schoolYear The school year of the student. This is an Integer that represents the school year of the student.
 */
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
