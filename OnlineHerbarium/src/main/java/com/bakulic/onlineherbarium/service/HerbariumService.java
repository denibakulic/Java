package com.bakulic.onlineherbarium.service;

import com.bakulic.onlineherbarium.model.Plant;
import com.bakulic.onlineherbarium.repository.HerbariumRepository;
import com.bakulic.onlineherbarium.repository.PlantRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Data
@Service
public class HerbariumService {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(HerbariumService.class);

    @Autowired
    private HerbariumRepository herbariumRepository;

    @Autowired
    private PlantRepository plantRepository;


    /** remove plant from herbarium*/
    public void removeHerbariumPlants (int id, int idPlant){
        List<Plant> plantList = plantRepository.findAll();
        Plant plant = plantRepository.findById(idPlant);
        if (plantList.contains(plant)){
            plantList.remove(plant);
        }
    }

}
