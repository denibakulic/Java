package com.bakulic.onlineherbarium.controller;

import com.bakulic.onlineherbarium.model.Plant;
import com.bakulic.onlineherbarium.model.UserList;
import com.bakulic.onlineherbarium.model.dto.CreateOrUpdatePlantDTO;
import com.bakulic.onlineherbarium.service.PlantService;
import com.bakulic.onlineherbarium.service.UserListService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.stream.IntStream;

@AllArgsConstructor
@Controller
@RequestMapping(value = "/plant")
public class PlantController {

    @Autowired
    private final PlantService plantService;

    @Autowired
    private final UserListService userListService;

    @GetMapping
    public String getPlantForm(Model model){
        CreateOrUpdatePlantDTO newPlant = new CreateOrUpdatePlantDTO();
        model.addAttribute("plant", newPlant);
        return "createPlantForm";
    }

    @PostMapping
    public String createPlant(@ModelAttribute("plant")  CreateOrUpdatePlantDTO createPlantDTO){
        plantService.createPlant(createPlantDTO);
        return "redirect:plant/all";
    }

    @GetMapping("update/{id}")
    public String getUpdatePlantForm(Model model, @PathVariable("id") int id){
        Plant plant = plantService.getPlantRepository().findById(id);
        model.addAttribute("plant", plant);
        return "updatePlantForm";
    }

    @PostMapping("/update/{id}")
    public String UpdatePlant(@PathVariable ("id") int id, @ModelAttribute("plant") CreateOrUpdatePlantDTO updatePlantDTO){
        plantService.updatePlant(id, updatePlantDTO);
        return "redirect:/plant/all";
    }

    @GetMapping("/all")
    public String getPlantList(Model model) {
        List<Plant> list = plantService.getAllPlants();
        model.addAttribute("plants", list);
        return "plantList";
    }

    @GetMapping("/delete/{id}")
    public  String deletePlantById( @PathVariable ("id") int id){
        Collection <UserList> userLists = userListService.getUserListRepository().listOfAllUserListsByPlant(id);
        IntStream.range(0, userLists.size())
                        .forEach(index ->{
                            UserList list = userListService.getUserListRepository().findById(index+1);
                            List<Plant> plantListOfUserList = list.getPlants();
                            plantListOfUserList.remove(plantService.getPlantRepository().findById(id));
                        });
        plantService.getPlantRepository().deleteById(id);
        return "redirect:/plant/all";
    }

}
