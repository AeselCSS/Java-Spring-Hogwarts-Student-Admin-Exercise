package kea.exercise.hogwartsstudentadmin.edu.hogwarts.dto;

import java.util.List;

public record HouseResponseDTO(
        String name,
        String founder,
        List<String> colors
) { }
