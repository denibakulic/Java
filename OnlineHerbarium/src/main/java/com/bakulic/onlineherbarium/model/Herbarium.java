package com.bakulic.onlineherbarium.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "userHerbariums")
@Data
public class Herbarium {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int herbariumId;

    @Column(name = "titles")
    private String title;

    @Column(name = "notes")
    private String note;

    @ManyToOne
    @JoinColumn(name = "idUser")
    private User user;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "herbarium", cascade = CascadeType.ALL)
    private List<UserPlant> userPlantList;


}
