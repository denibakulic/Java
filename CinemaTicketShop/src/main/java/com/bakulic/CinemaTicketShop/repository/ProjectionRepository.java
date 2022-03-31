package com.bakulic.CinemaTicketShop.repository;

import com.bakulic.CinemaTicketShop.model.Projection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
@Component
public interface ProjectionRepository extends JpaRepository<Projection, Integer> {

    //popis projekcija nekog filma
    @Query("SELECT p.hall.name, p.date, p.startTime FROM Projection p WHERE p.movie.name = :name")
    Collection<Projection> listOfProjectionByMovie(String name);

    Projection findById(int id);


}
