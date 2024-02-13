package kea.exercise.hogwartsstudentadmin.edu.hogwarts.repositories;

import kea.exercise.hogwartsstudentadmin.edu.hogwarts.models.House;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HouseRepository extends JpaRepository<House, Long> {
    House findByName(String name);
}
