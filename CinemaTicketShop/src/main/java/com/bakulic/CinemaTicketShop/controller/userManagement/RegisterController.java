package com.bakulic.CinemaTicketShop.controller.userManagement;

import com.bakulic.CinemaTicketShop.model.dto.requests.RegisterUserAccountDTO;
import com.bakulic.CinemaTicketShop.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@Controller
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

    // register a new user's account: not all the user information are required
    @PostMapping
    public String registerNewUserAccount(@ModelAttribute("user") RegisterUserAccountDTO registerUserAccountDTO) {
        userService.register(registerUserAccountDTO);
        return "redirect:/login";
    }
}
