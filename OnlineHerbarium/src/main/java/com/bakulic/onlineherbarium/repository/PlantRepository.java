package com.bakulic.onlineherbarium.repository;

import com.bakulic.onlineherbarium.model.Plant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component
public interface PlantRepository extends JpaRepository<Plant, int> {
}
