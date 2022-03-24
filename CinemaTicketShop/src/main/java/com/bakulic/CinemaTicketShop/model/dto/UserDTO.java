package com.bakulic.CinemaTicketShop.model.dto;

import com.bakulic.CinemaTicketShop.model.User;
import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@Data
public class UserDTO implements Serializable {
    private int id;
    private String fullname;
    private String email;
    private String username;
    private String password;
    private String role;


    public UserDTO(User user) {
        if (user != null) {
            this.id = user.getUserId();
            this.fullname = user.getFullname();
            this.email = user.getEmail();
            this.username = user.getUsername();
            this.password = user.getPassword();
            this.role = user.getRole();

        }
    }
}

