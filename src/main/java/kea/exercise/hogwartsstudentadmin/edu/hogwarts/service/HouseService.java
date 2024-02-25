package kea.exercise.hogwartsstudentadmin.edu.hogwarts.service;

import kea.exercise.hogwartsstudentadmin.edu.hogwarts.dto.HouseResponseDTO;
import kea.exercise.hogwartsstudentadmin.edu.hogwarts.model.House;

import java.util.List;

/**
 * Interface for the HouseService class
 * @see kea.exercise.hogwartsstudentadmin.edu.hogwarts.service.HouseServiceImpl
 */
public interface HouseService {
    /**
     * Method to find all houses
     * @return a list of all houses
     */
    List<HouseResponseDTO> findAllHouses();

    /**
     * Method to find a house by name
     * @param name
     * @return a house
     */
    HouseResponseDTO findHouseByName(String name);

    /**
     * Method to convert a house to a DTO
     * @param house
     * @return a houseResponseDTO
     */
    HouseResponseDTO toDTO(House house);
}
