package com.bakulic.CinemaTicketShop.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "movies")
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int movieId;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column (name = "length")
    private String length;

    @Column(name = "picture")
    private String picture;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "movie", cascade = CascadeType.ALL)
    private List<Projection> projectionList;


}