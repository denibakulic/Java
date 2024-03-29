package com.bakulic.onlineherbarium.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Plant {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int plantId;

    @Column(name = "species")
    private String species;

    @Column(name = "site")
    private String site;

    @Column(name ="habitat")
    private String habitat;

    @Column(name = "date")
    private String date;

    @Column(name = "image")
    private String image;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "idFamily")
    private Family family;

    @ManyToOne
    @JoinColumn(name = "idHerbarium")
    private Herbarium herbarium;


}
