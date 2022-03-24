package com.bakulic.CinemaTicketShop.controller.userManagement;

import com.bakulic.CinemaTicketShop.model.User;
import com.bakulic.CinemaTicketShop.model.dto.requests.CreateOrUpdateUserDTO;
import com.bakulic.CinemaTicketShop.model.dto.requests.RegisterUserAccountDTO;
import com.bakulic.CinemaTicketShop.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private final UserService userService;

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

    // register a new user's account: not all the user information are required
    @PostMapping
    public String savCreateUserForm (@ModelAttribute("newUser") RegisterUserAccountDTO registerUserAccountDTO) {
        userService.register(registerUserAccountDTO);
        return "userList";
    }

    @GetMapping("/update/{id}")
    public String getUpdateUserForm(Model model, @PathVariable ("id") int id) {
        User user = userService.getUserRepository().findById(id);
        model.addAttribute("user", user);
        return "updateUserForm";
    }
    @PostMapping("/update/{id}")
    public String saveUpdateUser(@ModelAttribute("user") CreateOrUpdateUserDTO createOrUpdateUserDTO, @PathVariable("id") int id) {
        //User user = userService.getUserRepository().findByUsername(createOrUpdateUserDTO.getUsername());
        userService.updateUser(createOrUpdateUserDTO, id);
        return "userList";
    }


    @GetMapping("/all")
    public String getUserList(Model model) {
        List<User> list = userService.getAllUsers();
        model.addAttribute("users",list);
        return "userList";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUserById(@PathVariable("id") int id, Model model) {
        userService.getUserRepository().deleteById(id);
        return "userList";
    }
}

