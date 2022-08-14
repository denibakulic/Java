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

    /**
     * list of all users
     */
    public List<Herbarium> getAllHerbariums() {
        return herbariumRepository.findAll();
    }


    /** create Herbarium **/
    public Herbarium createHerbarium(CreateOrUpdateHerbariumDTO createHerbariumDTO){
        if(createHerbariumDTO == null){
            throw new InvalidDataException("Herbarium cannot be null");
        }
        Herbarium newHerbarium = new Herbarium();
        newHerbarium.setTitle(createHerbariumDTO.getTitle());
        newHerbarium.setDescription(createHerbariumDTO.getDescription());
        newHerbarium.setDate(createHerbariumDTO.getDateCreated());

        Herbarium herbariumCreated = herbariumRepository.save(newHerbarium);
        log.info(String.format("Herbarium %s has been created.", herbariumCreated.getTitle()));
        return herbariumCreated;

    }



    /**update Herbarium*/
    public Herbarium updateHerbarium(int id, CreateOrUpdateHerbariumDTO updateHerbariumDTO){

        if (updateHerbariumDTO == null) {
            throw new InvalidDataException("Update data cannot be null");
        }
        Herbarium herbarium = herbariumRepository.findById(id);
        if (herbarium ==null) {
            throw new ObjectNotFoundException(String.format("The herbarium with Id = %s doesn't exists", id));
        }

        herbarium.setTitle(updateHerbariumDTO.getTitle());
        herbarium.setDescription(updateHerbariumDTO.getDescription());

        Herbarium herbariumUpdated = herbariumRepository.save(herbarium);
        log.info(String.format("Herbarium %s has been updated.", herbarium.getTitle()));
        return herbariumUpdated;
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
