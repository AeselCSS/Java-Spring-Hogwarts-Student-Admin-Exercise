package kea.exercise.hogwartsstudentadmin.edu.hogwarts.model;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import kea.exercise.hogwartsstudentadmin.edu.hogwarts.validator.MaxListSize;

import static kea.exercise.hogwartsstudentadmin.edu.hogwarts.utility.StringUtility.*;

import java.util.List;

@Entity
public class House {
    @Id
    private @Enumerated(EnumType.STRING) HouseName name;
    private String founder;

    @ElementCollection
    @MaxListSize(value = 2, message = "You can only have up to 2 colors")
    private List<String> colors;

    // Constructors
    public House() {
    }

    public House(HouseName name, String founder, List<String> colors) {
        this.name = name;
        this.founder = founder;
        this.colors = colors;
    }

    public HouseName getName() {
        return name;
    }

    public String getHouseName() {
        return toTitleCase(name.toString());
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
