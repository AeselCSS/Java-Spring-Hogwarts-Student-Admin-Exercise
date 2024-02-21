package kea.exercise.hogwartsstudentadmin.edu.hogwarts.dto;

import kea.exercise.hogwartsstudentadmin.edu.hogwarts.model.HouseName;

import static kea.exercise.hogwartsstudentadmin.edu.hogwarts.utility.StringUtility.*;

import java.util.List;

public class HouseDTO {
    private HouseName name;
    private String founder;
    private List<String> colors;

    public HouseDTO() {
    }

    public HouseDTO(HouseName name, String founder, List<String> colors) {
        this.name = name;
        this.founder = founder;
        this.colors = colors;
    }

    public String getName() {
        return toTitleCase(name.name());
    }

    public void setName(HouseName name) {
        this.name = name;
    }

    public String getFounder() {
        return founder;
    }

    public void setFounder(String founder) {
        this.founder = founder;
    }

    public List<String> getColors() {
        return colors;
    }

    public void setColors(List<String> colors) {
        this.colors = colors;
    }
}