package kea.exercise.hogwartsstudentadmin.edu.hogwarts.repository;

import kea.exercise.hogwartsstudentadmin.edu.hogwarts.model.House;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * This interface extends JpaRepository and provides CRUD operations for the House entity.
 * It is a part of the repository layer of the application, which is responsible for data access.
 *
 * @see org.springframework.data.jpa.repository.JpaRepository
 */
public interface HouseRepository extends JpaRepository<House, String> {
    // No additional methods are declared, so all CRUD operations are provided by JpaRepository.
}