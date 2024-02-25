package kea.exercise.hogwartsstudentadmin.edu.hogwarts.dto;

import java.util.List;

/**
 * Data Transfer Object for HouseResponse
 */
public record HouseResponseDTO(
        String name,
        String founder,
        List<String> colors
) { }
