package com.bakulic.onlineherbarium.controller;

import com.bakulic.onlineherbarium.model.Family;
import com.bakulic.onlineherbarium.model.Plant;
import com.bakulic.onlineherbarium.model.dto.CreateOrUpdatePlantDTO;
import com.bakulic.onlineherbarium.service.FamilyService;
import com.bakulic.onlineherbarium.service.PlantService;
import com.bakulic.onlineherbarium.service.UserListService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@AllArgsConstructor
@Controller
@RequestMapping(value = "/plant")
public class PlantController {

    @Autowired
    private final PlantService plantService;

    @Autowired
    private final FamilyService familyService;

    @ModelAttribute("plant")
    public CreateOrUpdatePlantDTO createOrUpdatePlantDTO(){return new CreateOrUpdatePlantDTO();}

    @GetMapping
    public String getPlantForm(Model model){
        CreateOrUpdatePlantDTO newPlant = new CreateOrUpdatePlantDTO();
        List<Family> familyList = familyService.getAllFamilies();
        model.addAttribute("families", familyList);
        model.addAttribute("plant", newPlant);
        return "create-plant";
    }

    @PostMapping
    public String createPlant(CreateOrUpdatePlantDTO createPlantDTO){
        plantService.createPlant(createPlantDTO);
        return "redirect:plant/all";
    }

    @GetMapping("/all")
    public String getPlantList(Model model) {
        List<Plant> plants= plantService.getAllPlants();
        model.addAttribute("plants", plants);
        return "all-plants";
    }

    @GetMapping("/{name}")
    public String getPlantByName(Model model, @PathVariable("name") String name) {
        Plant plant = plantService.getPlantRepository().findBySpecies(name);
        model.addAttribute("plant", plant);
        return "plant-page";
    }

    @GetMapping("/delete/{id}")
    public  String deletePlantById( @PathVariable ("id") int id){
        plantService.getPlantRepository().deleteById(id);
        return "redirect:/plant/all";

    }

}
