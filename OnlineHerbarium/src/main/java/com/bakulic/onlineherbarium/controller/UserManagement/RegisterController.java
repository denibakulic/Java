package com.bakulic.onlineherbarium.controller.UserManagement;

import com.bakulic.onlineherbarium.model.User;
import com.bakulic.onlineherbarium.model.dto.RegisterUserAccountDTO;
import com.bakulic.onlineherbarium.service.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
@AllArgsConstructor
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    private UserServiceImpl userService;

    @ModelAttribute("user")
    public RegisterUserAccountDTO registerUserAccountDTO(){return new RegisterUserAccountDTO();}

    @GetMapping
    public String RegistrationForm() {
        return "register";
    }

    @PostMapping
    public String registerNewUserAccount(@ModelAttribute("user") RegisterUserAccountDTO registerUserAccountDTO, BindingResult result) {
        if (result.hasErrors()) {
            return "redirect:/register?error";
        }
        List<User> allUsers = userService.getUserRepository().findAll();
        if (allUsers.isEmpty() == false) {
            for (int i = 0; i < allUsers.size(); i++) {
                if (allUsers.get(i).getUsername().equals(registerUserAccountDTO.getUsername()))
                    return "redirect:/register?usernameError";
            }
        }
        userService.register(registerUserAccountDTO);
        return "redirect:/register?success";
    }
}
