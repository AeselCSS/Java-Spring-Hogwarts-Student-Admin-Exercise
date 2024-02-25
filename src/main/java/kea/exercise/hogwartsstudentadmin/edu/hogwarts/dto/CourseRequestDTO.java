package kea.exercise.hogwartsstudentadmin.edu.hogwarts.dto;

import java.util.List;

import static kea.exercise.hogwartsstudentadmin.edu.hogwarts.utility.StringUtility.toTitleCase;

/**
 * Data transfer object for the Course model
 * <p>
 *     This record is used to transfer data related to a course request.
 *     It includes information about the course such as the course id, subject, school year, current status,
 *     teacher id, teacher name, student ids, and student names.
 *     The student ids and student names are stored in separate lists.
 *     The student ids are used to identify the students in the database, while the student names are used to display the students in the user interface.
 *
 *     @param id The unique identifier for the course.
 *     @param subject The subject of the course. This is a String that represents the subject of the course.
 *     @param schoolYear The school year of the course. This is an Integer that represents the school year of the course.
 *     @param isCurrent The current status of the course. This is a Boolean that represents whether the course is current or not.
 *     @param teacherId The unique identifier for the teacher of the course.
 *     @param teacherName The name of the teacher of the course. This is a String that represents the name of the teacher of the course.
 *     @param studentIds The list of unique identifiers for the students in the course. This is a List of Long objects that contains the unique identifiers for the students in the course.
 *     @param studentNames The list of names for the students in the course. This is a List of String objects that contains the names for the students in the course.
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
    public CourseRequestDTO {
        if (subject != null) {
            subject = toTitleCase(subject);
        }
    }
}
