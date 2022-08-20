package com.bakulic.onlineherbarium.service;

import com.bakulic.onlineherbarium.exceptions.InvalidDataException;
import com.bakulic.onlineherbarium.exceptions.ObjectNotFoundException;
import com.bakulic.onlineherbarium.model.Family;
import com.bakulic.onlineherbarium.model.Plant;
import com.bakulic.onlineherbarium.model.dto.CreateOrUpdatePlantDTO;
import com.bakulic.onlineherbarium.repository.FamilyRepository;
import com.bakulic.onlineherbarium.repository.PlantRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Data
@Service
public class PlantService {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(PlantService.class);

    @Autowired
    private PlantRepository plantRepository;

    @Autowired
    public PlantRepository getPlantRepository(){
        return plantRepository;
    }

    @Autowired
    private FamilyRepository familyRepository;

    @Autowired
    public FamilyRepository getFamilyRepository(){
        return familyRepository;
    }


    /** create plant **/
    public Plant createPlant(CreateOrUpdatePlantDTO createPlantDTO){
        if(createPlantDTO == null){
            throw new InvalidDataException("Plant cannot be null");
        }
        Plant plant = new Plant();
        Family family = familyRepository.findByName(createPlantDTO.getName());
        plant.setFamily(family);
        plant.setSpecies(createPlantDTO.getSpecies());
        plant.setSite(createPlantDTO.getSite());
        plant.setHabitat(createPlantDTO.getHabitat());
        plant.setDescription(createPlantDTO.getDescription());
        plant.setDate(createPlantDTO.getDate());
        plant.setImage(createPlantDTO.getImage());

        Plant plantCreated = plantRepository.save(plant);
        log.info(String.format("Plant %s has been created.", plant.getSpecies()));
        return plantCreated;

    }

    /**update plant*/
    public Plant updatePlant(int id, CreateOrUpdatePlantDTO updatePlantDTO){

        if (updatePlantDTO == null) {
            throw new InvalidDataException("Update data cannot be null");
        }
        Plant plant = plantRepository.findById(id);
        if (plant ==null) {
            throw new ObjectNotFoundException(String.format("The plant with Id = %s doesn't exists", id));
        }

        plant.setFamily(familyRepository.findByName(updatePlantDTO.getName()));
        plant.setSpecies(updatePlantDTO.getSpecies());
        plant.setSite(updatePlantDTO.getSite());
        plant.setHabitat(updatePlantDTO.getHabitat());
        plant.setDescription(updatePlantDTO.getDescription());
        plant.setDate(updatePlantDTO.getDate());
        plant.setImage(updatePlantDTO.getImage());


        Plant plantUpdated = plantRepository.save(plant);
        log.info(String.format("Plant %s has been updated.", plant.getSpecies()));
        return plantUpdated;
    }

    /**list of all plants*/
    public List<Plant> getAllPlants(){
        return plantRepository.findAll();
    }



}
