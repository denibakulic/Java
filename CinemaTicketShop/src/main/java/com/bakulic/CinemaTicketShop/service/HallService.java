package com.bakulic.CinemaTicketShop.service;

import com.bakulic.CinemaTicketShop.model.Hall;
import com.bakulic.CinemaTicketShop.model.dto.requests.CreateOrUpdateHallDTO;
import com.bakulic.CinemaTicketShop.repository.HallRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bakulic.CinemaTicketShop.exceptions.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

    /** hall create**/
    public Hall createHall(CreateOrUpdateHallDTO creteHallDTO){
        if(creteHallDTO == null){
            throw new InvalidDataException("Hall cannot be null");
        }
        Hall hall = new Hall();
        hall.setName(creteHallDTO.getName());
        hall.setNumberOfSeats(creteHallDTO.getNumberOfSeats());
        hall.setDescription(creteHallDTO.getDescription());

        Hall hallCreated = hallRepository.save(hall);
        log.info(String.format("Hall %s has been created.", hall.getName()));
        return hallCreated;

    }

    /**update hall*/
    public Hall updateHall(int id, CreateOrUpdateHallDTO updateHallDTO){

        if (updateHallDTO == null) {
            throw new InvalidDataException("Hall data cannot be null");
        }
        Hall hall = hallRepository.findById(id);
        if (hall ==null) {
            throw new ObjectNotFoundException(String.format("The hall with Id = %s doesn't exists", id));
        }

        hall.setName(updateHallDTO.getName());
        hall.setNumberOfSeats(updateHallDTO.getNumberOfSeats());
        hall.setDescription(updateHallDTO.getDescription());

        Hall hallUpdate = hallRepository.save(hall);
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


    /**delete hall*/
    public void deleteHallById(int id){

        Hall hall = hallRepository.findById(id);
        if (hall == null) {
            throw new ObjectNotFoundException(String.format("User not found with Id = %s", id));
        }

        hallRepository.deleteById(id);
        log.info(String.format("Hall %s has been deleted.", id));
    }
}

