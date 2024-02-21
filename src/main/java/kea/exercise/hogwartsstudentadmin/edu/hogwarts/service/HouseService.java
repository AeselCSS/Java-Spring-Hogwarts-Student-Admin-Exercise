package kea.exercise.hogwartsstudentadmin.edu.hogwarts.service;

import kea.exercise.hogwartsstudentadmin.edu.hogwarts.dto.HouseDTO;
import kea.exercise.hogwartsstudentadmin.edu.hogwarts.model.House;

import java.util.List;

public interface HouseService {
    List<HouseDTO> findAllHouses();
    HouseDTO findHouseByName(String name);

    // Convert the House entity to a HouseDTO
    HouseDTO convertToHouseDTO(House house);
}
