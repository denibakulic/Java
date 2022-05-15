package com.bakulic.onlineherbarium.contoroller.userManagement;

import com.bakulic.onlineherbarium.model.dto.CreateOrUpdateUserDTO;
import com.bakulic.onlineherbarium.model.dto.RegisterUserAccountDTO;
import com.bakulic.onlineherbarium.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@AllArgsConstructor
@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private final UserService userService;

    @ModelAttribute("user")
    public CreateOrUpdateUserDTO createOrUpdateUserDTO(){return new CreateOrUpdateUserDTO();}


    @ModelAttribute("newUser")
    public RegisterUserAccountDTO registerUserAccountDTO(){
        return new RegisterUserAccountDTO();}

    @GetMapping
    public String getCreateUserForm(Model model){
        RegisterUserAccountDTO newUser = new RegisterUserAccountDTO();
        model.addAttribute("user", newUser);
        return "createUser";
    }

    // register a new user's account: not all the user information are required
    @PostMapping
    public String savCreateUserForm (@ModelAttribute("newUser") RegisterUserAccountDTO registerUserAccountDTO) {
        userService.register(registerUserAccountDTO);
        return "redirect:user/all";
    }

    @GetMapping("/update/{id}")
    public String getUpdateUserForm(Model model, @PathVariable ("id") int id) {
        var user = userService.getUserRepository().findById(id);
        model.addAttribute("user", user);
        return "updateUserForm";
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
        var user = userService.getUserRepository().findById(id);
        userService.getUserRepository().deleteById(id);
        return "redirect:/user/all";
    }
}

