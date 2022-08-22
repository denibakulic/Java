package com.bakulic.onlineherbarium.repository;

import com.bakulic.onlineherbarium.model.Image;
import com.bakulic.onlineherbarium.model.Plant;
import com.bakulic.onlineherbarium.model.UserList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface PlantRepository extends JpaRepository<Plant, Integer> {

    Plant findById(int id);

    Plant findBySpecies(String name);


    @Query("FROM Plant p WHERE p.family.familyId = :id")
    Collection<Plant> listOfAllPlantsByFamily(int id);

    @Query("SELECT p FROM Plant p JOIN p.lists list JOIN list.plants plants WHERE list.listId = :listId")
    Collection<Plant> listOfAllPlantsByListId (@Param("listId") int listId);
}
