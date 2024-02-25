package kea.exercise.hogwartsstudentadmin.edu.hogwarts.repository;

import kea.exercise.hogwartsstudentadmin.edu.hogwarts.model.House;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * HouseRepository interface
 * This interface extends JpaRepository and is used to perform CRUD operations on the House entity
 */
public interface HouseRepository extends JpaRepository<House, String> {
}
