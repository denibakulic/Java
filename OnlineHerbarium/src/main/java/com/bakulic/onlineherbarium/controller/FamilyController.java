package com.bakulic.onlineherbarium.controller;

import com.bakulic.onlineherbarium.model.Family;
import com.bakulic.onlineherbarium.model.Plant;
import com.bakulic.onlineherbarium.model.dto.CreateOrUpdateFamilyDTO;
import com.bakulic.onlineherbarium.model.dto.CreateOrUpdateUserDTO;
import com.bakulic.onlineherbarium.service.FamilyService;
import com.bakulic.onlineherbarium.service.PlantService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/family")
public class FamilyController {

    @Autowired
    private final FamilyService familyService;

    @Autowired
    private final PlantService plantService;

    @ModelAttribute("family")
    public CreateOrUpdateFamilyDTO createOrUpdateFamilyDTO(){return new CreateOrUpdateFamilyDTO();}

    @GetMapping
    public String getFamilyForm(Model model){
        CreateOrUpdateFamilyDTO newFamily = new CreateOrUpdateFamilyDTO();
        model.addAttribute("family", newFamily);
        return "create-family";
    }

    @PostMapping
    public String createFamily(@ModelAttribute("family")  CreateOrUpdateFamilyDTO createFamilyDTO){
        familyService.createFamily(createFamilyDTO);
        return "redirect:family/all";
    }

    @GetMapping("update/{id}")
    public String getUpdateFamilyForm(Model model, @PathVariable("id") int id){
        Family family = familyService.getFamilyRepository().findById(id);
        model.addAttribute("family", family);
        return "update-family";
    }

    @PostMapping("/update/{id}")
    public String UpdateFamily(@PathVariable ("id") int id, @ModelAttribute("family") CreateOrUpdateFamilyDTO updateFamilyDTO){
        familyService.updateFamily(id, updateFamilyDTO);
        return "redirect:/family/all";
    }

    @GetMapping("/all")
    public String getFamilyList(Model model) {
        List<Family> list = familyService.getAllFamilies();
        model.addAttribute("families", list);
        return "all-families";
    }

    @GetMapping("/search")
    public String getFamilyListSearch(Model model) {
        List<Family> list = familyService.getAllFamilies();
        model.addAttribute("families", list);
        return "search";
    }

    @GetMapping("/delete/{id}")
    public  String deleteFamilyById( @PathVariable ("id") int id){
        List<Plant> allPlantList = plantService.getAllPlants();
        Collection<Plant> plantList = plantService.getPlantRepository().listOfAllPlantsByFamily(id);
        allPlantList.removeAll(plantList);
        familyService.getFamilyRepository().deleteById(id);
        return "redirect:/family/all";
    }
}
