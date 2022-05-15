package com.bakulic.onlineherbarium.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "userPlants")
@Data
public class UserPlant {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int userPlantId;

    @Column(name = "descriptions")
    private String plantDescription;

    @Column(name = "uploadPictures")
    private String picture;

    @ManyToOne
    @JoinColumn(name = "idHerbarium")
    private Herbarium herbarium;

}