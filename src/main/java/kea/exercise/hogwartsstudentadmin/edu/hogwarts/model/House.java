package kea.exercise.hogwartsstudentadmin.edu.hogwarts.model;

import jakarta.persistence.*;
import kea.exercise.hogwartsstudentadmin.edu.hogwarts.validator.MaxListSize;

import java.util.List;

@Entity
public class House {
    @Id
    private String name;
    private String founder;
    @ElementCollection
    @MaxListSize(value = 2, message = "You can only have up to 2 colors")
    private List<String> colors;

    // Constructors
    public House() {
    }

    public House(String name, String founder, List<String> colors) {
        this.name = name;
        this.founder = founder;
        this.colors = colors;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
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
