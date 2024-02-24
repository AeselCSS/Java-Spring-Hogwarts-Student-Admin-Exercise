package kea.exercise.hogwartsstudentadmin.edu.hogwarts.dto;

import java.util.List;

import static kea.exercise.hogwartsstudentadmin.edu.hogwarts.utility.StringUtility.toTitleCase;

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
    public CourseRequestDTO {
        if (subject != null) {
            subject = toTitleCase(subject);
        }
    }
}
