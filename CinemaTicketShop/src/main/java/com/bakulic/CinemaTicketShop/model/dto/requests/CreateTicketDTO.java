package com.bakulic.CinemaTicketShop.model.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateTicketDTO implements Serializable {

    //aditional info
    private String username;
    private String date;
    private String startTime;
    private String hallName;
    private String movieName;
    private String seatNumber;
    private String status;


}
