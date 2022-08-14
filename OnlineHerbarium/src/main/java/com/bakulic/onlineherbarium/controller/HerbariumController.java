package com.bakulic.onlineherbarium.controller;

import com.bakulic.onlineherbarium.model.Herbarium;
import com.bakulic.onlineherbarium.model.Plant;
import com.bakulic.onlineherbarium.model.dto.CreateOrUpdateHerbariumDTO;
import com.bakulic.onlineherbarium.model.dto.CreateOrUpdateUserDTO;
import com.bakulic.onlineherbarium.service.HerbariumService;
import com.bakulic.onlineherbarium.service.PlantService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;


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
    public String getHerbariumForm(Model model){
        CreateOrUpdateHerbariumDTO newHerbarium = new CreateOrUpdateHerbariumDTO();
        model.addAttribute("herbarium", newHerbarium);
        return "create-herbarium";
    }

    @PostMapping
    public String createHerbarium(@ModelAttribute("herbarium")  CreateOrUpdateHerbariumDTO createHerbariumDTO){
        herbariumService.createHerbarium(createHerbariumDTO);
        return "redirect:herbarium/all";
    }

    @GetMapping("update/{id}")
    public String getUpdateHerbariumForm(Model model, @PathVariable("id") int id){
        Herbarium herbarium = herbariumService.getHerbariumRepository().findById(id);
        Collection<Plant> plantList = plantService.getPlantRepository().listOfHerbariumPlants(id);
        model.addAttribute("plantList", plantList);
        model.addAttribute("herbarium", herbarium);
        return "update-herbarium";
    }

    @PostMapping("/update/{id}")
    public String UpdateHerbarium(@PathVariable ("id") int id, @ModelAttribute("hall") CreateOrUpdateHerbariumDTO updateHerbariumDTO){
        herbariumService.updateHerbarium(id,updateHerbariumDTO);
        return "redirect:/herbarium/all";
    }

    @GetMapping("/all")
    public String getHerbariumList(Model model) {
        List<Herbarium> list = herbariumService.getAllHerbariums();
        model.addAttribute("herbarium", list);
        return "all-herbariums";
    }

    @GetMapping("/view/{id}")
    public String getHerbariumById(Model model, @PathVariable("id") int id) {
        Herbarium herbarium = herbariumService.getHerbariumRepository().findById(id);
        Collection<Plant> plantList = plantService.getPlantRepository().listOfHerbariumPlants(id);
        model.addAttribute("plantList", plantList);
        model.addAttribute("herbarium", herbarium);
        return "herbarium-page";
    }

    @GetMapping("/plants/{id}")
    public String getHerbariumPlants(Model model, @PathVariable("id") int id) {
        Collection<Plant> plantList = plantService.getPlantRepository().listOfHerbariumPlants(id);
        model.addAttribute("plantList", plantList);
        return "plant-page";
    }

    @GetMapping("/plants/{id}/{plantId}")
    public String removeHerbariumPlants (@PathVariable ("id") int id, @PathVariable ("plantId") int plantId){
       herbariumService.removeHerbariumPlants(id, plantId);
        return "redirect:/herbarium/view/{id}";
    }

    @GetMapping("/delete/{id}")
    public  String deleteHerbariumById( @PathVariable ("id") int id){
        herbariumService.getHerbariumRepository().deleteById(id);
        return "redirect:/herbarium/all";
    }
}
