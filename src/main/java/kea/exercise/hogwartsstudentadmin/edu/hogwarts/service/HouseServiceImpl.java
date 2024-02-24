package kea.exercise.hogwartsstudentadmin.edu.hogwarts.service;

import kea.exercise.hogwartsstudentadmin.edu.hogwarts.dto.HouseResponseDTO;
import kea.exercise.hogwartsstudentadmin.edu.hogwarts.exception.ResourceNotFoundException;
import kea.exercise.hogwartsstudentadmin.edu.hogwarts.model.House;
import kea.exercise.hogwartsstudentadmin.edu.hogwarts.repository.HouseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HouseServiceImpl implements HouseService{
    private final HouseRepository houseRepository;

    public HouseServiceImpl(HouseRepository houseRepository) {
        this.houseRepository = houseRepository;
    }

    @Override
    public List<HouseResponseDTO> findAllHouses() {
        return houseRepository.findAll().stream().map(this::toDTO).toList();
    }

    @Override
    public HouseResponseDTO findHouseByName(String name) {
        return houseRepository.findById(name).map(this::toDTO).orElseThrow(() -> new ResourceNotFoundException("House not found"));
    }

    @Override
    public HouseResponseDTO toDTO(House house) {
        return new HouseResponseDTO(house.getName(), house.getFounder(), house.getColors());
    }
}