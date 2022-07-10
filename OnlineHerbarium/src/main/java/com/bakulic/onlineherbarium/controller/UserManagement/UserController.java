package com.bakulic.onlineherbarium.controller.UserManagement;

import com.bakulic.onlineherbarium.model.Image;
import com.bakulic.onlineherbarium.model.User;
import com.bakulic.onlineherbarium.model.UserList;
import com.bakulic.onlineherbarium.model.dto.CreateOrUpdateUserDTO;
import com.bakulic.onlineherbarium.model.dto.RegisterUserAccountDTO;
import com.bakulic.onlineherbarium.service.ImageService;
import com.bakulic.onlineherbarium.service.UserListService;
import com.bakulic.onlineherbarium.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@AllArgsConstructor
@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private final UserService userService;

    @Autowired
    private final ImageService imageService;

    @Autowired
    private final UserListService userListService;

    @ModelAttribute("user")
    public CreateOrUpdateUserDTO createOrUpdateUserDTO(){return new CreateOrUpdateUserDTO();}

    @ModelAttribute("newUser")
    public RegisterUserAccountDTO registerUserAccountDTO(){return new RegisterUserAccountDTO();}

    @GetMapping
    public String getCreateUserForm(Model model){
        RegisterUserAccountDTO newUser = new RegisterUserAccountDTO();
        model.addAttribute("user", newUser);
        return "createUser";
    }

    @PostMapping
    public String savCreateUserForm (@ModelAttribute("newUser") RegisterUserAccountDTO registerUserAccountDTO) {
        userService.register(registerUserAccountDTO);
        return "redirect:user/all";
    }

    @GetMapping("/update/{id}")
    public String getUpdateUserForm(Model model, @PathVariable("id") int id) {
        var user = userService.getUserRepository().findById(id);
        model.addAttribute("user", user);
        return "updateUser";
    }
    @PostMapping("/update/{id}")
    public String saveUpdateUser(@ModelAttribute("user") CreateOrUpdateUserDTO createOrUpdateUserDTO, @PathVariable("id") int id) {
        userService.updateUser(createOrUpdateUserDTO, id);
        return "redirect:/user/all";
    }


    @GetMapping("/all")
    public String getUserList(Model model) {
        var list = userService.getAllUsers();
        model.addAttribute("users",list);
        return "userList";
    }

    @GetMapping("/delete/{id}")
    public String deleteUserById(@PathVariable("id") int id) {
        List<Image> allImageList = imageService.getAllImages();
        Collection<Image> imageList = imageService.getAllImagesByUser(id);
        allImageList.removeAll(imageList);

        List<UserList> allIUserLists = userListService.getAllUserLists();
        Collection<UserList> userLists = userListService.getAllListsByUser(id);
        allIUserLists.removeAll(userLists);

        userService.getUserRepository().deleteById(id);
        return "redirect:/user/all";
    }

}
