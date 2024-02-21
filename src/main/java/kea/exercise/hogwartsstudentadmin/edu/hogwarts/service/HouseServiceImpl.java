package kea.exercise.hogwartsstudentadmin.edu.hogwarts.service;

import kea.exercise.hogwartsstudentadmin.edu.hogwarts.dto.HouseDTO;
import kea.exercise.hogwartsstudentadmin.edu.hogwarts.model.House;
import kea.exercise.hogwartsstudentadmin.edu.hogwarts.model.HouseName;
import kea.exercise.hogwartsstudentadmin.edu.hogwarts.repository.HouseRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HouseServiceImpl implements HouseService{
    private final HouseRepository houseRepository;

    public HouseServiceImpl(HouseRepository houseRepository) {
        this.houseRepository = houseRepository;
    }

    @Override
    public List<HouseDTO> findAllHouses() {
        List<House> houses =  houseRepository.findAll();
        List<HouseDTO> houseDTOS = new ArrayList<>();
        for (House house : houses) {
            houseDTOS.add(convertToHouseDTO(house));
        }
        return houseDTOS;
    }

    @Override
    public HouseDTO findHouseByName(String name) {
        House house =  houseRepository.findByName(HouseName.valueOf(name.toUpperCase().trim()));
        return convertToHouseDTO(house);
    }

    @Override
    public HouseDTO convertToHouseDTO(House house) {
        return new HouseDTO(house.getName(), house.getFounder(), house.getColors());
    }


}