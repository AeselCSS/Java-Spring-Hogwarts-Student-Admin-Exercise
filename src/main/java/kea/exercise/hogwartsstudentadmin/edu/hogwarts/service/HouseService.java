package kea.exercise.hogwartsstudentadmin.edu.hogwarts.service;

import kea.exercise.hogwartsstudentadmin.edu.hogwarts.dto.HouseResponseDTO;
import kea.exercise.hogwartsstudentadmin.edu.hogwarts.model.House;

import java.util.List;

/**
 * This interface defines the contract for the HouseService.
 * It provides methods for CRUD operations on House entities and for converting between House and HouseResponseDTO objects.
 *
 * @see kea.exercise.hogwartsstudentadmin.edu.hogwarts.service.HouseServiceImpl
 */
public interface HouseService {
    /**
     * Retrieves all houses from the database.
     *
     * @return a list of all houses, each represented as a HouseResponseDTO
     */
    List<HouseResponseDTO> findAllHouses();

    /**
     * Retrieves a house by their name.
     *
     * @param name the name of the house to retrieve
     * @return the house with the given name, represented as a HouseResponseDTO
     */
    HouseResponseDTO findHouseByName(String name);

    /**
     * Converts a House entity to a HouseResponseDTO.
     *
     * @param house the house to convert
     * @return the house, represented as a HouseResponseDTO
     */
    HouseResponseDTO toDTO(House house);
}