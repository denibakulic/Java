package com.bakulic.onlineherbarium.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Herbarium {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int herbariumId;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "herbarium", cascade = CascadeType.ALL)
    private List<Plant> plantList;


}
