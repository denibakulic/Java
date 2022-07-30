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

    @Query("FROM Family f WHERE f.familyId = :id")
    Collection<Plant> listOfAllPlantsByFamily(int id);

    @Query("FROM Family f WHERE f.name = :name")
    Collection<Plant> listOfAllPlantsByFamilyName(String name);

    @Query("FROM Herbarium h WHERE h.title = :title")
    Collection<Plant> listOfHerbariumPlants(String title);

    @Query("FROM Plant p JOIN UserList us where us.listId = :listId")
    List<Plant> findAllByListId(@Param("listId")int listId);

}
