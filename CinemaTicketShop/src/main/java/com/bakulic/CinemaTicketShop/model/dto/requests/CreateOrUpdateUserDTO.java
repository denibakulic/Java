package com.bakulic.CinemaTicketShop.model.dto.requests;

import com.bakulic.CinemaTicketShop.model.Ticket;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateOrUpdateUserDTO implements Serializable {

    private String username;
    private String password;
    private String fullname;
    private String email;
    private String role;

}
