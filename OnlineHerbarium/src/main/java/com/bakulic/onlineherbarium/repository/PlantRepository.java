package com.bakulic.onlineherbarium.repository;

import com.bakulic.onlineherbarium.model.Image;
import com.bakulic.onlineherbarium.model.Plant;
import com.bakulic.onlineherbarium.model.UserList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface PlantRepository extends JpaRepository<Plant, Integer> {

    Plant findById(int id);

    Plant findBySpecies(String name);

    @Query("FROM Family f WHERE f.familyId = :id")
    Collection<Plant> listOfAllPlantsByFamily(int id);

    @Query("FROM Herbarium h WHERE h.title = :title")
    Collection<Plant> listOfHerbariumPlants(String title);


}
