package kea.exercise.hogwartsstudentadmin.edu.hogwarts.model;

import jakarta.persistence.*;
import kea.exercise.hogwartsstudentadmin.edu.hogwarts.validator.MaxListSize;

import java.util.List;

/**
 * The House class is a model that represents a house in the Hogwarts Student Admin system.
 * It contains information about the name, founder, and colors of the house.
 */
@Entity
public class House {
    /**
     * The name of the house.
     * It is a string that represents the name of the house.
     * It is the primary key of the house entity.
     * It is not nullable.
     */
    @Id
    private String name;

    /**
     * The founder of the house.
     * It is a string that represents the founder of the house.
     */
    private String founder;

    /**
     * The colors of the house.
     * It is a list of strings that represents the colors of the house.
     * It is a many-to-many relationship with the Color model.
     * It is fetched eagerly.
     * It is validated to have a maximum size of 2.
     */
    @ElementCollection
    @MaxListSize(value = 2, message = "You can only have up to 2 colors")
    private List<String> colors;

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
