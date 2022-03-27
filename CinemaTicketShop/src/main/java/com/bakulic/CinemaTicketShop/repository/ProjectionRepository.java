package com.bakulic.CinemaTicketShop.repository;

import com.bakulic.CinemaTicketShop.model.Projection;
import com.bakulic.CinemaTicketShop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.UUID;

@Repository
@Component
public interface ProjectionRepository extends JpaRepository<Projection, Integer> {

    //popis projekcija nekog filma
    @Query("SELECT p.hall.name, p.date, p.startTime FROM Projection p WHERE p.movie.name = :name")
    Collection<Projection> listOfProjectionByMovie(String name);

    //popis projekcija neke dvorane
    @Query("SELECT p.movie.name, p.date, p.startTime FROM Projection p WHERE p.hall.name = :name")
    Collection<Projection> listOfProjectionByHall(String name);

    //projekcije po datumu
    @Query("SELECT p.movie.name, p.hall.name, p.startTime FROM Projection p WHERE p.date = :date")
    Collection<Projection> listOfProjectionByDate(String date);

    //projekcije u određeno vrijeme
    @Query("SELECT p.movie.name, p.hall.name, p.date FROM Projection p WHERE p.startTime = :time")
    Collection<Projection> listOfProjectionsByTime(String time);

    Projection findById(int id);

    Projection findByMovie_Name(String name);

}
