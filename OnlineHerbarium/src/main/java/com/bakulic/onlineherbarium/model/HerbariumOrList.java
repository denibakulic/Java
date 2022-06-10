package com.bakulic.onlineherbarium.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "herbarium_or_list")
@Data
public class HerbariumOrList {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int herbariumOrListId;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "pictures") //check what type of data to use
    private String picture;

    @ManyToMany
    @JoinTable(
            name = "herbarium_plant",
            joinColumns = @JoinColumn(name = "herbarium_id"),
            inverseJoinColumns = @JoinColumn(name = "plant_id"))
    List<Plant> plantList;

    @ManyToOne
    @JoinColumn(name = "idUser")
    private User user;

    @Column(name = "date")
    private String date;

    @ManyToOne
    @JoinColumn(name = "idType")
    private Type type;
}
