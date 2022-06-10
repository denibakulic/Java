package com.bakulic.onlineherbarium.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "type")
@Data
public class Type {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int typeId;

    @Column(name = "name")
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "type", cascade = CascadeType.ALL)
    private List<HerbariumOrList> herbariumOrLists;
}
