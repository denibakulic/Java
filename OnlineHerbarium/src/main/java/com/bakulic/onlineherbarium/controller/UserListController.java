package com.bakulic.onlineherbarium.controller;

import com.bakulic.onlineherbarium.model.Plant;
import com.bakulic.onlineherbarium.model.UserList;
import com.bakulic.onlineherbarium.model.dto.CreateOrUpdateUserListDTO;
import com.bakulic.onlineherbarium.service.PlantService;
import com.bakulic.onlineherbarium.service.UserListService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

@AllArgsConstructor
@Controller
@RequestMapping(value = "/userlist")
public class UserListController {

    @Autowired
    private final UserListService userListService;

    @Autowired
    private final PlantService plantService;

    @ModelAttribute("userList")
    public CreateOrUpdateUserListDTO createOrUpdateUserListDTO(){return new CreateOrUpdateUserListDTO();}

    @GetMapping
    public String getUserListForm(Model model){
        CreateOrUpdateUserListDTO newUserList = new CreateOrUpdateUserListDTO();
        model.addAttribute("userList", newUserList);
        return "create-userlist";
    }

    @PostMapping
    public String createUserList(@ModelAttribute("userList")  CreateOrUpdateUserListDTO createUserListDTO){
        userListService.createUserList(createUserListDTO);
        return "redirect:userlist/list/{id}"; //kako poslat na ovu putanju
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
        return "redirect:/userlist/all";
    }

    @GetMapping("/all")
    public String getAllUserLists(Model model) {
        List<UserList> list = userListService.getAllUserLists();
        model.addAttribute("userLists", list);
        return "user-lists";
    }

    @GetMapping("/user/{id}")
    public String getUserListByUser(Model model, @PathVariable("id") int id){
        Collection <UserList> usersLists = userListService.getAllListsByUser(id);
        model.addAttribute("usersLists", usersLists);
        return "update-userlist";
    }

    @GetMapping("/list/{id}")
    public String getUserListPage(Model model, @PathVariable int id){
        UserList us = userListService.getUserListRepository().findById(id);
        model.addAttribute("list", us);
        List<Plant> plantList = plantService.getPlantRepository().findAllByListId(id);
        model.addAttribute("plantList", plantList);
        return "userlist-page";
    }

    @GetMapping("/add/{userListId}")
    public String addPlantToList(@PathVariable int userListId, Model model) {
        UserList userList=userListService.getUserListRepository().findById(userListId);
        List<Plant> userPlants = plantService.getPlantRepository().findAllByListId(userListId);
        List<Plant> plants = plantService.getAllPlants();
        List<Plant> displayPlants = new LinkedList<>();
        plants.stream().forEach(i -> {
            if(!userPlants.contains(i)){
                displayPlants.add(i);
            }
        });
        model.addAttribute("userList", userList);
        model.addAttribute("userPlants", userPlants);
        model.addAttribute("plants", displayPlants);
        return "checkbox";
    }

    @PostMapping("/add/{userListId}")
    public String savePlantsToList(@PathVariable int userListId){
       // UserList userList = userListService.getUserListRepository().findById(userListId);
     //   userListService.getUserListRepository().save(userList);
        return "redirect:/userlist/list/{userListId}";
    }


    @GetMapping("/list/{id}/remove/{plantId}") //provjerit da li radi
    public String removePlantFromList(@PathVariable int id, @PathVariable int plantId) {
        userListService.removePlantFromList(id, plantId);
        return "redirect:/userlist/list/{id}";
    }

    @GetMapping("/delete/{id}")
    public  String deleteUserListById( @PathVariable ("id") int id){
        userListService.getUserListRepository().deleteById(id);
        return "redirect:/userlist/all";
    }

}
