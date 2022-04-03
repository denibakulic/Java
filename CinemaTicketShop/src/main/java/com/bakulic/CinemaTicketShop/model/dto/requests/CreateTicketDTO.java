package com.bakulic.CinemaTicketShop.model.dto.requests;

import com.bakulic.CinemaTicketShop.model.Projection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;



@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateTicketDTO implements Serializable {


    private String username;
    private String date;
    private String startTime;
    private String hallName;
    private String movieName;
    private String seatNumber;


}
