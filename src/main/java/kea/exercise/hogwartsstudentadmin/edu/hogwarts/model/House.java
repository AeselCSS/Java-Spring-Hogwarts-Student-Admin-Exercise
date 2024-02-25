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

    /**
     * Default constructor for the House class.
     */
    public House() {
    }

    /**
     * Constructor for the House class with all fields.
     * @param name The name of the house.
     * @param founder The founder of the house.
     * @param colors The colors of the house.
     */
    public House(String name, String founder, List<String> colors) {
        this.name = name;
        this.founder = founder;
        this.colors = colors;
    }

    /**
     * Getter for the name of the house.
     * @return The name of the house.
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for the name of the house.
     * @param name The new name of the house.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter for the founder of the house.
     * @return The founder of the house.
     */
    public String getFounder() {
        return founder;
    }

    /**
     * Setter for the founder of the house.
     * @param founder The new founder of the house.
     */
    public void setFounder(String founder) {
        this.founder = founder;
    }

    /**
     * Getter for the colors of the house.
     * @return The colors of the house.
     */
    public List<String> getColors() {
        return colors;
    }

    /**
     * Setter for the colors of the house.
     * @param colors The new colors of the house.
     */
    public void setColors(List<String> colors) {
        this.colors = colors;
    }
}