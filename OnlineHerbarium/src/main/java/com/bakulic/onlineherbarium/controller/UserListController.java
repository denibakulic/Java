package com.bakulic.onlineherbarium.controller;

import com.bakulic.onlineherbarium.model.Plant;
import com.bakulic.onlineherbarium.model.User;
import com.bakulic.onlineherbarium.model.UserList;
import com.bakulic.onlineherbarium.model.dto.CreateOrUpdateUserListDTO;
import com.bakulic.onlineherbarium.service.PlantService;
import com.bakulic.onlineherbarium.service.UserListService;
import com.bakulic.onlineherbarium.service.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@AllArgsConstructor
@Controller
@RequestMapping(value = "/userlist")
public class UserListController {

    @Autowired
    private final UserListService userListService;

    @Autowired
    private final PlantService plantService;

    @Autowired
    private final UserServiceImpl userService;

    @ModelAttribute("userList")
    public CreateOrUpdateUserListDTO createOrUpdateUserListDTO(){return new CreateOrUpdateUserListDTO();}

    @GetMapping("/{id}")
    public String getUserListForm(Model model, @PathVariable int id){
        User user = userService.getUserRepository().findById(id);
        CreateOrUpdateUserListDTO newUserList = new CreateOrUpdateUserListDTO();
        model.addAttribute("userList", newUserList);
        model.addAttribute("user", user);
        return "create-userlist";
    }

    @PostMapping("/{id}")
    public String createUserList(CreateOrUpdateUserListDTO createUserListDTO, @PathVariable int id){
        userListService.createUserList(createUserListDTO, id);
        return "redirect:/userlist/user/{id}";
    }

    @GetMapping("/update/{id}")
    public String getUpdateUserListForm(Model model, @PathVariable int id){
        UserList userList = userListService.getUserListRepository().findById(id);
        model.addAttribute("userList", userList);
        return "update-userlist";
    }

    @PostMapping("/update/{id}")
    public String updateUserList(@PathVariable ("id") int id, @ModelAttribute("userList") CreateOrUpdateUserListDTO updateUserListDTO){
        userListService.updateUserList(id, updateUserListDTO);
        return "redirect:/userlist/list/{id}";
    }

    @GetMapping("/user/{id}")
    public String getUserListByUser(Model model, @PathVariable ("id") int id){
        Collection<UserList> userLists =userListService.getAllListsByUser(id);
        User user = userService.getUserRepository().findById(id);
        model.addAttribute("user", user);
        model.addAttribute("userLists", userLists);
        return "user-lists";
    }

    @GetMapping("/list/{id}")
    public String getUserListPage(Model model, @PathVariable int id){
        UserList us = userListService.getUserListRepository().findById(id);
        model.addAttribute("list", us);
        List<Plant> plants = us.getPlants();
        model.addAttribute("plantList", plants);
        return "userlist-page";
    }

    @GetMapping("/add/{id}")
    public String addPlantToListForm(Model model, @PathVariable int id) {
        UserList userList=userListService.getUserListRepository().findById(id);
        List<Plant> userPlants = userList.getPlants();
        List<Plant> plants = plantService.getAllPlants();
        List<Plant> displayPlants = new ArrayList<>();

        plants.forEach(i -> {
            if(!userPlants.contains(i)){
                displayPlants.add(i);
            }
        });
        model.addAttribute("userList", userList);
        model.addAttribute("userPlants", userPlants);
        model.addAttribute("plants", displayPlants);
        return "checkbox";
    }

    @PostMapping("/add/{id}")
    public String addPlantToList(@PathVariable int id,
                                 @RequestParam(value = "plant" , required = false) List<Plant> plants) {
        plants.forEach(plant ->{
            userListService.addPlantsToList(id, plant.getPlantId());

        });
        return "redirect:/userlist/list/{id}";
    }

    @GetMapping("/remove/{id}")
    public String removePlantFromListForm(Model model, @PathVariable int id) {
        UserList userList=userListService.getUserListRepository().findById(id);
        List<Plant> userPlants = userList.getPlants();
        System.err.println(userPlants.get(0).getPlantId());

        model.addAttribute("userList", userList);
        model.addAttribute("plants", userPlants);
        return "checkbox-remove";
    }

    @PostMapping("/remove/{id}")
    public String removePlantFromList(@PathVariable int id,
                                      @RequestParam(value = "plant" , required = false) List<Plant> plants) {
        plants.forEach(plant ->{
            userListService.removePlantFromList(id, plant.getPlantId());

        });
        return "redirect:/userlist/list/{id}";
    }

    @GetMapping("/delete/{id}")
    public  String deleteUserListById(@PathVariable ("id") int id){
        userListService.getUserListRepository().deleteById(id);
        return "redirect:/";
    }

}
