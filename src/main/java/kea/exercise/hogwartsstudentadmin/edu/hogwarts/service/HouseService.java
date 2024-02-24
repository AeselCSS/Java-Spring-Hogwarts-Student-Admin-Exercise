package kea.exercise.hogwartsstudentadmin.edu.hogwarts.service;

import kea.exercise.hogwartsstudentadmin.edu.hogwarts.dto.HouseResponseDTO;
import kea.exercise.hogwartsstudentadmin.edu.hogwarts.model.House;

import java.util.List;

public interface HouseService {
    List<HouseResponseDTO> findAllHouses();
    HouseResponseDTO findHouseByName(String name);

    HouseResponseDTO toDTO(House house);
}
