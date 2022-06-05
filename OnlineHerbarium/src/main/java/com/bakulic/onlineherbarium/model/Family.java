package com.bakulic.onlineherbarium.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "families")
@Data
public class Family {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int familyId;

    @Column(name = "species")
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "family", cascade = CascadeType.ALL)
    private List<Plant> list;
}
