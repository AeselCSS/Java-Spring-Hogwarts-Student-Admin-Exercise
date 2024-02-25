package kea.exercise.hogwartsstudentadmin.edu.hogwarts.dto;

import java.util.List;

import static kea.exercise.hogwartsstudentadmin.edu.hogwarts.utility.StringUtility.toTitleCase;

/**
 * Data Transfer Object for CourseRequest
 */
public record CourseRequestDTO(
        Long id,
        String subject,
        Integer schoolYear,
        Boolean isCurrent,
        Long teacherId,
        String teacherName,
        List<Long> studentIds,
        List<String> studentNames
) {

    /**
     * Constructor for CourseRequestDTO
     * Capitalizes the subject
     * @param id
     * @param subject
     * @param schoolYear
     * @param isCurrent
     * @param teacherId
     * @param teacherName
     * @param studentIds
     * @param studentNames
     */
    public CourseRequestDTO {
        if (subject != null) {
            subject = toTitleCase(subject);
        }
    }
}
