package kea.exercise.hogwartsstudentadmin.edu.hogwarts.service;

import kea.exercise.hogwartsstudentadmin.edu.hogwarts.dto.HouseResponseDTO;
import kea.exercise.hogwartsstudentadmin.edu.hogwarts.model.House;

import java.util.List;

/**
 * Interface for the HouseService class
 * @see kea.exercise.hogwartsstudentadmin.edu.hogwarts.service.HouseServiceImpl
 */
public interface HouseService {
    List<HouseResponseDTO> findAllHouses();
    HouseResponseDTO findHouseByName(String name);

    HouseResponseDTO toDTO(House house);
}
