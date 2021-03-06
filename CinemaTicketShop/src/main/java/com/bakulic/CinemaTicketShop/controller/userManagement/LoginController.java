package com.bakulic.CinemaTicketShop.controller.userManagement;

import com.bakulic.CinemaTicketShop.model.Movie;
import com.bakulic.CinemaTicketShop.model.Projection;
import com.bakulic.CinemaTicketShop.model.User;
import com.bakulic.CinemaTicketShop.model.dto.requests.LoginUserAccountDTO;
import com.bakulic.CinemaTicketShop.service.MovieService;
import com.bakulic.CinemaTicketShop.service.UserService;
import com.bakulic.CinemaTicketShop.service.ProjectionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@Controller
@Slf4j
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private MovieService movieService;

    @Autowired
    private ProjectionService projectionService;

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
            return "adminhome";
        } else
            userService.login(loginUserAccountDTO);
            List<Movie> list = movieService.getAllMovies();
            List<Projection> listProj = projectionService.getAllProjections();
            model.addAttribute("movies", list);
            model.addAttribute("projections", listProj);
            return "home";
    }



}

