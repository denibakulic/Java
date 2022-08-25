package com.bakulic.onlineherbarium.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "userList")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserList {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int listId;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "date")
    private LocalDateTime date;

    @ManyToMany
    @JoinTable(
            name = "plant_list",
            joinColumns = @JoinColumn(name = "plantId", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "listId", referencedColumnName = "id"))
    List<Plant> plants;

    @ManyToOne
    @JoinColumn(name = "idUser")
    private User user;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "userList", cascade = CascadeType.ALL)
    private List<Image> imageList;

}
