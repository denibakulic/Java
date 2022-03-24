package com.bakulic.CinemaTicketShop.model.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginUserAccountDTO implements Serializable {

    private String username;
    private String password;

}

