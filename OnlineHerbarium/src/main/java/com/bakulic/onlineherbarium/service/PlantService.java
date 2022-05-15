package com.bakulic.onlineherbarium.service;

import com.bakulic.onlineherbarium.exceptions.InvalidDataException;
import com.bakulic.onlineherbarium.model.Herbarium;
import com.bakulic.onlineherbarium.model.Plant;
import com.bakulic.onlineherbarium.model.dto.CreateOrUpdateHerbariumDTO;
import com.bakulic.onlineherbarium.model.dto.CreateOrUpdatePlantDTO;
import com.bakulic.onlineherbarium.repository.HerbariumRepository;
import com.bakulic.onlineherbarium.repository.PlantRepository;
import com.bakulic.onlineherbarium.repository.UserRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Data
@Service
public class PlantService {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(Herbarium.class);

    @Autowired
    private final PlantRepository plantRepository;

    @Autowired
    public PlantRepository getPlantRepository (){return plantRepository;};

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    public UserRepository getUserRepository (){return userRepository;};

    /** crate herbarium**/
    public Herbarium createPlant(CreateOrUpdatePlantDTO createPlantDTO){
        if(createPlantDTO == null){
            throw new InvalidDataException("Herbarium cannot be null");
        }
        var plant = new Plant();
        plant.se

        var newHerbarium = herbariumRepository.save(herbarium);
        log.info(String.format("Herbarium %s has been created.", herbarium.getHerbariumId()));
        return newHerbarium;
    }

    /** update herbarium**/ //title mora biti unique, ali onda se ne može mijenjat?
    public Herbarium updateHerbarium(CreateOrUpdateHerbariumDTO updateHerbariumDTO) throws Exception {
        if(updateHerbariumDTO == null){
            throw new InvalidDataException("Herbarium cannot be null");
        }
        var herbarium = herbariumRepository.findByTitle(updateHerbariumDTO.getTitle());
        herbarium.setNote(updateHerbariumDTO.getNote());
        herbarium.setUserPlantList(updateHerbariumDTO.getPlantList());
        var user = userRepository.findByUsername(updateHerbariumDTO.getUsername());
        herbarium.setUser(user);
        //picture

        var newHerbarium = herbariumRepository.save(herbarium);
        log.info(String.format("Herbarium %s has been created.", herbarium.getHerbariumId()));
        return newHerbarium;
    }

    /**list of all herbariums*/
    public List<Herbarium> getAllHerbarium(){
        return herbariumRepository.findAll();
    }

    //delete, payiti na kaskadno brisanje
}
