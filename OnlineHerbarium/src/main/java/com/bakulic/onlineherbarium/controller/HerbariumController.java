package com.bakulic.onlineherbarium.controller;

import com.bakulic.onlineherbarium.model.Herbarium;
import com.bakulic.onlineherbarium.model.Plant;
import com.bakulic.onlineherbarium.model.dto.CreateOrUpdateHerbariumDTO;
import com.bakulic.onlineherbarium.service.HerbariumService;
import com.bakulic.onlineherbarium.service.PlantService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;


@AllArgsConstructor
@Controller
@RequestMapping(value = "/herbarium")
public class HerbariumController {

    @Autowired
    private final HerbariumService herbariumService;

    @Autowired
    private final PlantService plantService;

    @ModelAttribute("herbarium")
    public CreateOrUpdateHerbariumDTO createOrUpdateHerbariumDTO(){return new CreateOrUpdateHerbariumDTO();}


    @GetMapping
    public String getHerbarium(Model model) {
        Herbarium herbarium = herbariumService.getHerbariumRepository().findById(1);
        Collection<Plant> plantList = plantService.getAllPlants();
        model.addAttribute("plantList", plantList);
        model.addAttribute("herbarium", herbarium);
        return "herbarium-page";
    }

    @GetMapping("/admin")
    public String getHerbariumAdmin(Model model) {
        Herbarium herbarium = herbariumService.getHerbariumRepository().findById(1);
        Collection<Plant> plantList = plantService.getAllPlants();
        model.addAttribute("plantList", plantList);
        model.addAttribute("herbarium", herbarium);
        return "herbarium-pageadmin";
    }

    @GetMapping("/plants/{plantId}")
    public String removeHerbariumPlants (@PathVariable ("plantId") int plantId){
       plantService.getPlantRepository().deleteById(plantId);
        return "redirect:/herbarium/admin";
    }

}
