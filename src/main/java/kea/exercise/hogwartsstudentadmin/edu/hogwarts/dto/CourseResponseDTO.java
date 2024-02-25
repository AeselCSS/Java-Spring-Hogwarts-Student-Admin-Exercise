package kea.exercise.hogwartsstudentadmin.edu.hogwarts.dto;

import java.util.List;

/**
 * Data Transfer Object for CourseResponse.
 * <p>
 * This record is used to transfer data related to a course response.
 * It includes information about the course such as the course id, subject, school year, current status,
 * teacher response, and student responses.
 *
 * @param id The unique identifier for the course.
 * @param subject The subject of the course. This is a String that represents the subject of the course.
 * @param schoolYear The school year of the course. This is an Integer that represents the school year of the course.
 * @param isCurrent The current status of the course. This is a Boolean that represents whether the course is current or not.
 * @param teacher The response data for the teacher of the course. This is a TeacherResponseDTO object that contains the response data for the teacher.
 * @param students The list of response data for the students in the course. This is a List of StudentResponseDTO objects that contains the response data for the students.
 */
public record CourseResponseDTO(
        Long id,
        String subject,
        Integer schoolYear,
        Boolean isCurrent,
        TeacherResponseDTO teacher,
        List<StudentResponseDTO> students
) {
}
