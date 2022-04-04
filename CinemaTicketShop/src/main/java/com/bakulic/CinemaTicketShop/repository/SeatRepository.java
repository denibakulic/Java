package com.bakulic.CinemaTicketShop.repository;

import com.bakulic.CinemaTicketShop.model.Projection;
import com.bakulic.CinemaTicketShop.model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component
public interface SeatRepository extends JpaRepository<Seat, Integer> {

    Seat findByProjectionAndAndSeatNumber(Projection projId, Integer num);
}
