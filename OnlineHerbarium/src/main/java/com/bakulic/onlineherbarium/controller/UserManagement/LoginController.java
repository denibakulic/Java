package com.bakulic.onlineherbarium.controller.UserManagement;

import com.bakulic.onlineherbarium.model.User;
import com.bakulic.onlineherbarium.model.dto.LoginUserAccountDTO;
import com.bakulic.onlineherbarium.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@Slf4j
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UserService userService;


    @ModelAttribute("user")
    public LoginUserAccountDTO loginUserAccountDTO() {
        return new LoginUserAccountDTO();
    }


    @GetMapping
    public String login(Model model) {
        LoginUserAccountDTO user = new LoginUserAccountDTO();
        model.addAttribute("user", user);
        return "login";
    }

    @PostMapping
    public String loginUser (@ModelAttribute("user") LoginUserAccountDTO loginUserAccountDTO, Model model) {
        User user = userService.getUserRepository().findByUsername(loginUserAccountDTO.getUsername());
        if (user.getRole().equals("admin")) {
            userService.login(loginUserAccountDTO);
            return "adminHome";
        } else
            userService.login(loginUserAccountDTO);
        return "userHome";
    }
}
