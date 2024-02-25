package kea.exercise.hogwartsstudentadmin.edu.hogwarts.service;

import kea.exercise.hogwartsstudentadmin.edu.hogwarts.dto.HouseResponseDTO;
import kea.exercise.hogwartsstudentadmin.edu.hogwarts.exception.ResourceNotFoundException;
import kea.exercise.hogwartsstudentadmin.edu.hogwarts.model.House;
import kea.exercise.hogwartsstudentadmin.edu.hogwarts.repository.HouseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for the House entity
 */
@Service
public class HouseServiceImpl implements HouseService{
    private final HouseRepository houseRepository;

    /**
     * Constructor for the HouseService class
     * @param houseRepository
     */
    public HouseServiceImpl(HouseRepository houseRepository) {
        this.houseRepository = houseRepository;
    }

    /**
     * Method to find all houses
     * @return a list of all houses
     */
    @Override
    public List<HouseResponseDTO> findAllHouses() {
        return houseRepository.findAll().stream().map(this::toDTO).toList();
    }

    /**
     * Method to find a house by name
     * @param name
     * @return a house
     */
    @Override
    public HouseResponseDTO findHouseByName(String name) {
        return houseRepository.findById(name).map(this::toDTO).orElseThrow(() -> new ResourceNotFoundException("House not found"));
    }

    /**
     * Method to convert a house to a DTO
     * @param house
     * @return a house DTO
     */
    @Override
    public HouseResponseDTO toDTO(House house) {
        return new HouseResponseDTO(house.getName(), house.getFounder(), house.getColors());
    }
}