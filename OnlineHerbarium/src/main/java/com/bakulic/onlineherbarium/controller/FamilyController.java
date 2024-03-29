package com.bakulic.onlineherbarium.controller;

import com.bakulic.onlineherbarium.model.Family;
import com.bakulic.onlineherbarium.model.Plant;
import com.bakulic.onlineherbarium.model.dto.CreateOrUpdateFamilyDTO;
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
@Controller
@RequestMapping(value = "/families")
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
        return "redirect:families/all";
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
        return "redirect:/families/all";
    }

    @GetMapping("/all")
    public String getAllFamilies(Model model){
        List<Family> families = familyService.getFamilyRepository().findAllByOrderByNameAsc();
        model.addAttribute("families", families);
        return "all-families";
    }

    @GetMapping("/search")
    public String getFamilyListSearch(Model model) {
        List<Family> list = familyService.getAllFamilies();
        model.addAttribute("families", list);
        return "search";
    }

    @GetMapping("/search/{id}")
    public String getPlantByFamily(Model model, @PathVariable ("id") int id) {
        Collection<Plant> plants = plantService.getPlantRepository().listOfAllPlantsByFamily(id);
        model.addAttribute("plants", plants);
        return "search-result";
    }

    @GetMapping("/delete/{id}")
    public  String deleteFamilyById( @PathVariable ("id") int id){
        familyService.getFamilyRepository().deleteById(id);
        return "redirect:/families/all";
    }
}
