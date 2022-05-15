package com.bakulic.onlineherbarium.model;

import lombok.*;

import java.util.List;
import javax.persistence.*;

@Entity
@Data
@Table(name = "plants")
public class Plant {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int plantId;

    @Column(name = "families")
    private String family;

    @Column(name = "species")
    private String specie;

    @Column(name ="habitats")
    private String habitat;

    @Column(name = "collected")
    private String collected;

    @Column(name = "date")
    private String date;

    @Column(name = "pictures")
    private String picture;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "plant", cascade = CascadeType.ALL)
    private List<UserList> lists;

}
