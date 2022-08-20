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

import java.util.Collection;
import java.util.List;


@AllArgsConstructor
@Controller
@RequestMapping(value = "/plant")
public class PlantController {

    @Autowired
    private final PlantService plantService;

    @Autowired
    private final UserListService userListService;

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
    public String createPlant(@ModelAttribute("plant")  CreateOrUpdatePlantDTO createPlantDTO){
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

    @GetMapping("/{searchResult}/{id}")
    public String getPlantByFamily(Model model, @PathVariable ("id") int id) {
        Collection<Plant> plants = plantService.getPlantRepository().listOfAllPlantsByFamily(id);
        model.addAttribute("plants", plants);
        return "search-result";
    }

    @GetMapping("/delete/{id}") //popravit, ako se izbrise biljka mora se izbrisat od svugdi
    public  String deletePlantById( @PathVariable ("id") int id){
//        Collection <UserList> userLists = userListService.getUserListRepository().listOfAllUserListsByPlant(id);
//        IntStream.range(0, userLists.size())
//                        .forEach(index ->{
//                            UserList list = userListService.getUserListRepository().findById(index+1);
//                            List<Plant> plantListOfUserList = list.getPlants();
//                            plantListOfUserList.remove(plantService.getPlantRepository().findById(id));
//                        });
        plantService.getPlantRepository().deleteById(id);
        return "redirect:/plant/all";

    }

}
