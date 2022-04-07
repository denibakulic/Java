package com.bakulic.CinemaTicketShop.service;

import com.bakulic.CinemaTicketShop.model.Hall;
import com.bakulic.CinemaTicketShop.model.Projection;
import com.bakulic.CinemaTicketShop.model.Seat;
import com.bakulic.CinemaTicketShop.model.dto.requests.CreateOrUpdateHallDTO;
import com.bakulic.CinemaTicketShop.repository.HallRepository;
import com.bakulic.CinemaTicketShop.repository.ProjectionRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bakulic.CinemaTicketShop.exceptions.*;

import java.util.Collection;
import java.util.List;
import java.util.stream.IntStream;


@AllArgsConstructor
@Service
public class HallService {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(HallService.class);

    @Autowired
    private final HallRepository hallRepository;

    @Autowired
    public HallRepository getHallRepository(){
        return hallRepository;
    }

    @Autowired
    private final ProjectionRepository projectionRepository;

    @Autowired
    public ProjectionRepository getProjectionRepository(){
        return projectionRepository;
    }

    /** hall create**/
    public Hall createHall(CreateOrUpdateHallDTO creteHallDTO){
        if(creteHallDTO == null){
            throw new InvalidDataException("Hall cannot be null");
        }
        var hall = new Hall();
        hall.setName(creteHallDTO.getName());
        hall.setNumberOfSeats(creteHallDTO.getNumberOfSeats());
        hall.setDescription(creteHallDTO.getDescription());

        var hallCreated = hallRepository.save(hall);
        log.info(String.format("Hall %s has been created.", hall.getName()));
        return hallCreated;

    }

    /**update hall*/
    public Hall updateHall(int id, CreateOrUpdateHallDTO updateHallDTO){

        if (updateHallDTO == null) {
            throw new InvalidDataException("Hall data cannot be null");
        }
        var hall = hallRepository.findById(id);
        if (hall ==null) {
            throw new ObjectNotFoundException(String.format("The hall with Id = %s doesn't exists", id));
        }

        hall.setName(updateHallDTO.getName());
        hall.setNumberOfSeats(updateHallDTO.getNumberOfSeats());
        hall.setDescription(updateHallDTO.getDescription());

        var hallUpdate = hallRepository.save(hall);
        log.info(String.format("Hall %s has been updated.", hall.getName()));
        return hallUpdate;
    }

    /**list of all halls*/
    public List<Hall> getAllHalls(){
        return hallRepository.findAll();
    }


    /**get hall by name*/
    public Hall findHallByName(String name){
        if (name == null){
            throw  new InvalidDataException("Hall name cannot be null");
        }
        return hallRepository.findByName(name);
    }
}

