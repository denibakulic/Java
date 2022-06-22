package com.bakulic.CinemaTicketShop.controller.userManagement;

import com.bakulic.CinemaTicketShop.model.Ticket;
import com.bakulic.CinemaTicketShop.model.User;
import com.bakulic.CinemaTicketShop.model.dto.requests.CreateOrUpdateUserDTO;
import com.bakulic.CinemaTicketShop.model.dto.requests.RegisterUserAccountDTO;
import com.bakulic.CinemaTicketShop.service.TicketService;
import com.bakulic.CinemaTicketShop.service.UserService;
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
    private final TicketService ticketService;

    @ModelAttribute("user")
    public CreateOrUpdateUserDTO createOrUpdateUserDTO(){return new CreateOrUpdateUserDTO();}


    @ModelAttribute("newUser")
    public RegisterUserAccountDTO registerUserAccountDTO(){
        return new RegisterUserAccountDTO();}

    @GetMapping
    public String getCreateUserForm(Model model){
        RegisterUserAccountDTO newUser = new RegisterUserAccountDTO();
        model.addAttribute("user", newUser);
        return "register";
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
        Collection<Ticket> ticketList = ticketService.getTicketRepository().findByUsername(user.getUsername());
        ticketList.removeAll(ticketList);
        userService.getUserRepository().deleteById(id);

        return "redirect:/user/all";
    }
}

