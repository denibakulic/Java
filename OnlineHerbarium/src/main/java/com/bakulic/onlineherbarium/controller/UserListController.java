package com.bakulic.onlineherbarium.controller;

import com.bakulic.onlineherbarium.model.UserList;
import com.bakulic.onlineherbarium.model.dto.CreateOrUpdateUserListDTO;
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
@RequestMapping(value = "/userList")
public class UserListController {

    @Autowired
    private final UserListService userListService;

    @GetMapping
    public String getUserListForm(Model model){
        CreateOrUpdateUserListDTO newUserList = new CreateOrUpdateUserListDTO();
        model.addAttribute("userList", newUserList);
        return "createUserListForm";
    }

    @PostMapping
    public String createUserList(@ModelAttribute("userList")  CreateOrUpdateUserListDTO createUserListDTO){
        userListService.createUserList(createUserListDTO);
        return "redirect:userList/all";
    }

    @GetMapping("/update/{id}")
    public String getUpdateUserListForm(Model model, @PathVariable int id){
        UserList userList = userListService.getUserListRepository().findById(id);
        model.addAttribute("userList", userList);
        return "updateUserListForm";
    }

    @PostMapping("/update/{id}")
    public String UpdateUserList(@PathVariable ("id") int id, @ModelAttribute("userList") CreateOrUpdateUserListDTO updateUserListDTO){
        userListService.updateUserList(id, updateUserListDTO);
        return "redirect:/userList/all";
    }

    @GetMapping("/all")
    public String getAllUserList(Model model) {
        List<UserList> list = userListService.getAllUserLists();
        model.addAttribute("userLists", list);
        return "userListsList";
    }

    @GetMapping("/user")
    public String getUserListByUser(Model model, @PathVariable("id") int id){
        Collection <UserList> usersLists = userListService.getAllListsByUser(id);
        model.addAttribute("usersLists", usersLists);
        return "updateUserListForm";
    }

    @GetMapping("/delete/{id}")
    public  String deleteUserListById( @PathVariable ("id") int id){
        userListService.getUserListRepository().deleteById(id);
        return "redirect:/family/all";
    }

}
