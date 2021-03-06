package com.bakulic.CinemaTicketShop.model.dto.requests;

import com.bakulic.CinemaTicketShop.model.Projection;
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
public class CreateOrUpdateMovieDTO implements Serializable {

    private String name;
    private String description;
    private String length;
    private List<Projection> projectionList;
    private String picture;

}
