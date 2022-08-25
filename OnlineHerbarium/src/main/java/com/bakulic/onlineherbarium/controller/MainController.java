package com.bakulic.onlineherbarium.controller;

import com.bakulic.onlineherbarium.model.Role;
import com.bakulic.onlineherbarium.model.User;
import com.bakulic.onlineherbarium.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;


@Controller
@RequestMapping
public class MainController {

    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/logout")
    public String logout() {
        return "login";
    }

    @GetMapping("/")
    public String home(Model model) {
        User currentUser = userService.getUser();
        model.addAttribute("current", currentUser);

        Collection<Role> userRoles = currentUser.getRoles();
        for(Role r : userRoles){
            if(r.getName().equals("ADMIN_ROLE")){
                return "admin-home";
            }
        }
        return "user-home";
    }

}


