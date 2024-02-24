package kea.exercise.hogwartsstudentadmin.edu.hogwarts.controller;

import kea.exercise.hogwartsstudentadmin.edu.hogwarts.dto.HouseResponseDTO;
import kea.exercise.hogwartsstudentadmin.edu.hogwarts.service.HouseService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HouseController {

    private final HouseService houseService;

    public HouseController(HouseService houseService) {
        this.houseService = houseService;
    }

    @GetMapping("/houses")
    public List<HouseResponseDTO> getAllHouses() {
        return houseService.findAllHouses();
    }

    @GetMapping("/houses/{name}")
    public HouseResponseDTO getHouseByName(@PathVariable String name) {
        return houseService.findHouseByName(name);
    }

}