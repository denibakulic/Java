package com.bakulic.CinemaTicketShop.repository;

import com.bakulic.CinemaTicketShop.model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatRepository extends JpaRepository<Seat, Integer> {
}
