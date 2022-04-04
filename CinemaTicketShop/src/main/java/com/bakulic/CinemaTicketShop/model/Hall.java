package com.bakulic.CinemaTicketShop.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "halls")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Hall {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int hallId;

    @Column(name = "name")
    private String name;

    @Column(name = "numberofseats")
    private Integer numberOfSeats;

    @Column(name = "description")
    private String description;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "hall", cascade = CascadeType.ALL)
    private List<Projection> projectionList;

}
