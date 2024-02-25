package kea.exercise.hogwartsstudentadmin.edu.hogwarts.controller;

import kea.exercise.hogwartsstudentadmin.edu.hogwarts.dto.CourseRequestDTO;
import kea.exercise.hogwartsstudentadmin.edu.hogwarts.exception.SchoolYearMismatchException;
import kea.exercise.hogwartsstudentadmin.edu.hogwarts.service.CourseService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(CourseController.class)
public class CourseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CourseService courseService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testPostCourseWithMismatchedSchoolYear() throws Exception {
        // Given
        CourseRequestDTO courseRequestDTO = new CourseRequestDTO(10L, "Defense Against the Dark Arts", 3, false, null, null, null, null);

        // When the courseService tries to save a course with mismatched school year, it throws a SchoolYearMismatchException
        doThrow(new SchoolYearMismatchException("Mismatched school year")).when(courseService).saveCourse(any(CourseRequestDTO.class));

        // Then
        mockMvc.perform(post("/courses")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(courseRequestDTO)))
                .andExpect(status().isBadRequest()); // Expect a 400 Bad Request response

        // No need to verify the interaction with courseService.saveCourse here since doThrow already specifies the behavior
    }
}
