package kea.exercise.hogwartsstudentadmin.edu.hogwarts.dto;

import java.util.List;

/**
 * Data Transfer Object for HouseResponse
 * <p>
 *     This record is used to transfer data related to a house response.
 *     It includes information about the house such as the house name, founder, and colors.
 *
 *     @param name The name of the house. This is a String that represents the name of the house.
 *     @param founder The founder of the house. This is a String that represents the founder of the house.
 *     @param colors The list of colors for the house. This is a List of String objects that contains the colors for the house.
 */
public record HouseResponseDTO(
        String name,
        String founder,
        List<String> colors
) { }
