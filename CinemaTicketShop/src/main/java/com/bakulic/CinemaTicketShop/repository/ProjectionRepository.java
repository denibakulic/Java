package com.bakulic.CinemaTicketShop.repository;

import com.bakulic.CinemaTicketShop.model.Hall;
import com.bakulic.CinemaTicketShop.model.Projection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
@Component
public interface ProjectionRepository extends JpaRepository<Projection, Integer> {

    //popis projekcija nekog filma
    @Query ("FROM Projection p WHERE p.movie.name = :name")
    Collection<Projection> listOfProjectionByMovie(String name);

    Projection findById(int id);

    @Query ("FROM Projection p WHERE p.hall = :hall")
    Collection<Projection> listOfProjectionsByHall(Hall hall);
}
