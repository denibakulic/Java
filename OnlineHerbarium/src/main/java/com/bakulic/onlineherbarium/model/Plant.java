package com.bakulic.onlineherbarium.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "plants")
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

    @Column(name = "collected")
    private String collected;

    @Column(name = "date")
    private String date;

    @ManyToOne
    @JoinColumn(name = "idFamily")
    private Family family;

    @ManyToOne
    @JoinColumn(name = "idHerbarium")
    private Herbarium herbarium;

    @ManyToMany
    @JoinTable(
            name = "plant_list",
            joinColumns = @JoinColumn(name = "plantId"),
            inverseJoinColumns = @JoinColumn(name = "listId"))
    List<UserList> lists;
}
