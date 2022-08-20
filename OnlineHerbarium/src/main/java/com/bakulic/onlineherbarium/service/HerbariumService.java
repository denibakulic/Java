package com.bakulic.onlineherbarium.service;

import com.bakulic.onlineherbarium.exceptions.InvalidDataException;
import com.bakulic.onlineherbarium.exceptions.ObjectNotFoundException;
import com.bakulic.onlineherbarium.model.Herbarium;
import com.bakulic.onlineherbarium.model.Plant;
import com.bakulic.onlineherbarium.model.dto.CreateOrUpdateHerbariumDTO;
import com.bakulic.onlineherbarium.repository.HerbariumRepository;
import com.bakulic.onlineherbarium.repository.PlantRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@Service
public class HerbariumService {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(HerbariumService.class);

    @Autowired
    private HerbariumRepository herbariumRepository;

    @Autowired
    public HerbariumRepository getHerbariumRepository(){
        return herbariumRepository;
    }

    @Autowired
    private PlantRepository plantRepository;

    @Autowired
    public PlantRepository getPlantRepository(){
        return plantRepository;
    }


    @Autowired
    private PlantService plantService;
    private HerbariumService(PlantService plantService){
        this.plantService = plantService;
    }


    /** remove herbarium plants*/
    public void removeHerbariumPlants (int id, int idPlant){
        Collection<Plant> herbariumPlants = plantRepository.listOfHerbariumPlants(id);
        Plant plant = plantRepository.findById(idPlant);
        if (herbariumPlants.contains(plant)){
            herbariumPlants.remove(plant);
        }
    }

}
