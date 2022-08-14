package com.bakulic.onlineherbarium.controller.UserManagement;

import com.bakulic.onlineherbarium.model.dto.RegisterUserAccountDTO;
import com.bakulic.onlineherbarium.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@AllArgsConstructor
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    private UserService userService;

    @ModelAttribute("user")
    public RegisterUserAccountDTO registerUserAccountDTO(){return new RegisterUserAccountDTO();}

    @GetMapping
    public String registrationForm(Model model){
        RegisterUserAccountDTO newUser = new RegisterUserAccountDTO();
        model.addAttribute("user", newUser);
        return "register";
    }

    @PostMapping
    public String registerNewUserAccount(@ModelAttribute("user") RegisterUserAccountDTO registerUserAccountDTO) {
        userService.register(registerUserAccountDTO);
        return "redirect:/dashboard";
    }
}
