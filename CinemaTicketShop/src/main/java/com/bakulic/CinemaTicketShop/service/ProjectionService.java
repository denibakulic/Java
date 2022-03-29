package com.bakulic.CinemaTicketShop.service;

import com.bakulic.CinemaTicketShop.model.Hall;
import com.bakulic.CinemaTicketShop.model.Movie;
import com.bakulic.CinemaTicketShop.model.Projection;
import com.bakulic.CinemaTicketShop.model.Seat;
import com.bakulic.CinemaTicketShop.model.dto.requests.CreateOrUpdateProjectionDTO;
import com.bakulic.CinemaTicketShop.repository.ProjectionRepository;
import com.bakulic.CinemaTicketShop.service.validation.*;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.IntStream;

import com.bakulic.CinemaTicketShop.exceptions.*;

@AllArgsConstructor
@Service
public class ProjectionService {

    @Autowired
    private ProjectionRepository projectionRepository;

    @Autowired
    public ProjectionRepository getProjectionRepository(){
        return projectionRepository;
    }


    private TimeValidator timeValidator;
    private DateValidator dateValidator;

    public ProjectionService(){
        timeValidator = new TimeValidator();
        dateValidator = new DateValidator();
    }
    @Autowired
    private HallService hallService;
    private ProjectionService(HallService hallService){
        this.hallService = hallService;
    }

    @Autowired
    private MovieService movieService;
    private ProjectionService(MovieService movieService){
        this.movieService = movieService;
    }

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(UserService.class);

    /** list of all projection*/
    public List<Projection> getAllProjections (){return projectionRepository.findAll();}


    /** create projection*/
    public Projection createProjection(CreateOrUpdateProjectionDTO createProjectionDTO){
        if(createProjectionDTO == null){
            throw new InvalidDataException("Projection cannot be null"); //u kontrolere bad request
        }

        //timeValidator.checkTime(createProjectionDTO.getStartTime());
        //dateValidator.checkDate(createProjectionDTO.getDate());

        var proj = new Projection(); //object mapper, pretvotrba dto u entity(mapstruct)
        proj.setDate(createProjectionDTO.getDate());
        proj.setStartTime(createProjectionDTO.getStartTime());

        var hall = hallService.findHallByName(createProjectionDTO.getName());
        proj.setHall(hall);
        Integer numOfSeats = hall.getNumberOfSeats();

        Movie movie = movieService.getMovieByName(createProjectionDTO.getMovieName());
        proj.setMovie(movie);

        List<Seat> seatList = createSeatList(numOfSeats, proj);
        proj.setSeatList(seatList);

        Projection projCreated = projectionRepository.save(proj); //povratan informacija da li kreirano
        log.info(String.format("Projection %s has been created.", proj.getProjectionId()));
        return projCreated;

    }

    /** update projection*/
    public Projection updateProjection(int id, CreateOrUpdateProjectionDTO updateProjectionDTO) {

        if (updateProjectionDTO == null) {
            throw new InvalidDataException("Hall data cannot be null");
        }
        Projection proj = projectionRepository.findById(id);
        if (proj == null) {
            throw new ObjectNotFoundException(String.format("The Projection with Id = %s doesn't exists", id));
        }

        timeValidator.checkTime(updateProjectionDTO.getStartTime());
        dateValidator.checkDate(updateProjectionDTO.getDate());

        proj.setDate(updateProjectionDTO.getDate());
        proj.setStartTime(updateProjectionDTO.getStartTime());

        var hall = hallService.findHallByName(updateProjectionDTO.getName());
        proj.setHall(hall);

        Integer numOfSeats = hall.getNumberOfSeats();
        List<Seat> seatList = createSeatList(numOfSeats, proj);

        proj.setSeatList(seatList);

        Projection projUpdate = projectionRepository.save(proj);

        log.info(String.format("Projection %s has been updated.", proj.getProjectionId()));
        return projUpdate;
    }

    /** Create seat list*/

    public List<Seat> createSeatList(int num, Projection proj){
        List<Seat> seatList = new ArrayList<>();
        IntStream.range(1, num+1)
                .forEach(index -> {
                    var seat = new Seat();
                    seat.setSeatNumber(index);
                    seat.setProjection(proj);
                    seatList.add(seat);
                });
        return seatList;
    };

    /** get projections of a movie*/
    public Collection<Projection> getProjectionsByMovie(String name){
        if(name == null){
            throw  new InvalidDataException("Movie name cannot be null");
        }
        return  projectionRepository.listOfProjectionByMovie(name);
    }


    /** get projection by movie*//*
    public Projection getProjectionByMovie(String name){
        if(name == null){
            throw  new InvalidDataException("Movie name cannot be null");
        }
        List<Projection> projList = projectionRepository.findByMovie_Name(name);
        return  projectionRepository.findByMovie_Name(name);
    }
*/
    /**delete projection*/
    public void deleteProjectionById(int id){

        Projection proj = projectionRepository.findById(id);
        if (proj == null) {
            throw new ObjectNotFoundException(String.format("Projection not found with Id = %s", id));
        }

        projectionRepository.deleteById(id);
        log.info(String.format("Projection %s has been deleted.", id));
    }



}
