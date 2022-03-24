package com.bakulic.CinemaTicketShop.service;

import com.bakulic.CinemaTicketShop.model.Hall;
import com.bakulic.CinemaTicketShop.model.Movie;
import com.bakulic.CinemaTicketShop.model.Projection;
import com.bakulic.CinemaTicketShop.model.Seat;
import com.bakulic.CinemaTicketShop.model.dto.ProjectionDTO;
import com.bakulic.CinemaTicketShop.model.dto.requests.CreateOrUpdateProjectionDTO;
import com.bakulic.CinemaTicketShop.repository.ProjectionRepository;
import com.bakulic.CinemaTicketShop.repository.SeatRepository;
import com.bakulic.CinemaTicketShop.service.validation.*;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import com.bakulic.CinemaTicketShop.exceptions.*;

@AllArgsConstructor
@Service
public class ProjectionService {

    @Autowired
    private ProjectionRepository projectionRepository;
    @Autowired
    private SeatRepository seatRepository;

    @Autowired
    public ProjectionRepository getProjectionRepository(){
        return projectionRepository;
    }

    @Autowired
    public SeatRepository getSeatRepository(){return seatRepository;}

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
        if(hall == null){
            hall = new Hall();
        }
        proj.setHall(hall);
        Integer numOfSeats = hall.getNumberOfSeats();

        Movie movie = movieService.getMovieByName(createProjectionDTO.getName());
        if(movie == null){
            movie = new Movie();
        }
        proj.setMovie(movie);

        List<Seat> list = createProjectionDTO.getSeatList();
        if(list == null) {
            List<Seat> list1 = new LinkedList<>();
            for (int i = 1; i <= numOfSeats; i++) {
                Seat seat = new Seat();
                seat.setSeatNumber(i);
                seat.setStatus("empty");
                list1.add(seat);
            }
            createProjectionDTO.setSeatList(list1);
        }
        else{
            for (int i = 1; i <= numOfSeats; i++) {
                Seat seat = new Seat();
                seat.setSeatNumber(i);
                seat.setStatus("empty");
                list.add(seat);
            }
            createProjectionDTO.setSeatList(list);
        }

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

        //proj.setSeatList(updateProjectionDTO.getSeatList());
        Hall hall = proj.getHall();
        if (hall == null) {
            hall = new Hall();
        }
        hall.setName(updateProjectionDTO.getName());

        Movie movie = proj.getMovie();
        if (movie == null) {
            movie = new Movie();
        }
        movie.setName(updateProjectionDTO.getMovieName());

        List<Seat> list = updateProjectionDTO.getSeatList();
        Integer oldNumSeats = 0;
        for (int i =0;i< list.size(); i++){
            oldNumSeats = oldNumSeats+1;
        }
        Integer numSeat= hall.getNumberOfSeats();
        if(numSeat != oldNumSeats) {
            for (int i = 1; i <= numSeat; i++) {
                Seat seat = new Seat();
                seat.setSeatNumber(i);
                seat.setStatus("empty");
                list.add(seat);
            }
        }


        Projection projUpdate = projectionRepository.save(proj);

        log.info(String.format("Projection %s has been updated.", proj.getProjectionId()));
        return projUpdate;
    }

    /** get projections by movie*/
    public Collection<Projection> getProjectionsByMovie(String name){
        if(name == null){
            throw  new InvalidDataException("Movie name cannot be null");
        }
        return  projectionRepository.listOfProjectionByMovie(name);
    }

    /** get projections by hall*/

    public Collection<Projection> getProjectionsByHall(String name){
        if(name == null){
            throw  new InvalidDataException("Hall name cannot be null");
        }
        return  projectionRepository.listOfProjectionByHall(name);
    }

    /** get projections by date*/
    public Collection<Projection> getProjectionsByDate(String date){
        if(date == null){
            throw  new InvalidDataException("Date cannot be null");
        }
        return  projectionRepository.listOfProjectionByDate(date);
    }

    /** get projections by time*/
    public Collection<Projection> getProjectionsByTime(String time){
        if(time == null){
            throw  new InvalidDataException("Time cannot be null");
        }
        return  projectionRepository.listOfProjectionsByTime(time);
    }

    /** change reserved seat status to empty 1h before projection*/
    public List<Seat> resetSeatStatus(ProjectionDTO projectionDTO){
        List<Seat> list = projectionDTO.getSeatList();
        for(int i=0;i<list.size();i++){
            Seat seat = new Seat();
            if(seat.getStatus()== "reserved"){
                seat.setStatus("empty");
            }
        }
        return list;
    }

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
