package kea.exercise.hogwartsstudentadmin.edu.hogwarts.controller;

import kea.exercise.hogwartsstudentadmin.edu.hogwarts.dto.HouseResponseDTO;
import kea.exercise.hogwartsstudentadmin.edu.hogwarts.service.HouseService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * The HouseController class is a REST controller that handles HTTP requests for the House model.
 * It is responsible for handling requests for the House model and returning responses to the client.
 */
@RestController
public class HouseController {

    private final HouseService houseService;

    /**
     * Constructor for the HouseController class.
     * @param houseService The HouseService object that is used to handle the business logic for the House model.
     */
    public HouseController(HouseService houseService) {
        this.houseService = houseService;
    }

    /**
     * This method handles the HTTP GET request for the /houses endpoint.
     * It returns a list of all houses in the database.
     * @return A list of HouseResponseDTO objects.
     */
    @GetMapping("/houses")
    public List<HouseResponseDTO> getAllHouses() {
        return houseService.findAllHouses();
    }

    /**
     * This method handles the HTTP GET request for the /houses/{name} endpoint.
     * It returns a house with the specified name.
     * @param name The name of the house to return.
     * @return A HouseResponseDTO object.
     */
    @GetMapping("/houses/{name}")
    public HouseResponseDTO getHouseByName(@PathVariable String name) {
        return houseService.findHouseByName(name);
    }

}