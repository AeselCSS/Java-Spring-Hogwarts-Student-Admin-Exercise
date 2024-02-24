package kea.exercise.hogwartsstudentadmin.edu.hogwarts.dto;

import java.util.List;

public record CourseResponseDTO(
        Long id,
        String subject,
        Integer schoolYear,
        Boolean isCurrent,
        TeacherResponseDTO teacher,
        List<StudentResponseDTO> students
) {
}
