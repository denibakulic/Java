package com.bakulic.onlineherbarium.repository;

import com.bakulic.onlineherbarium.model.Plant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;


@Repository
public interface PlantRepository extends JpaRepository<Plant, Integer> {

    Plant findById(int id);

    Plant findBySpecies(String name);

    @Query("FROM Plant p WHERE p.family.familyId = :id")
    Collection<Plant> listOfAllPlantsByFamily(int id);

}
