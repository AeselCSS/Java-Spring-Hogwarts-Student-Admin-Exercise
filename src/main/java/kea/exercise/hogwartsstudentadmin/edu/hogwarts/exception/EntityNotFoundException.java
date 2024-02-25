package kea.exercise.hogwartsstudentadmin.edu.hogwarts.exception;

/**
 * Custom exception for when an entity is not found
 */
public class EntityNotFoundException extends RuntimeException {
    /**
     * Constructor
     * @param entityName name of the entity
     * @param id id of the entity
     */
    public EntityNotFoundException(String entityName, Long id) {
        super(entityName + " with id " + id + " not found");
    }
}
