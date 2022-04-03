package com.bakulic.CinemaTicketShop.service;

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

        timeValidator.checkTime(createProjectionDTO.getStartTime());
        dateValidator.checkDate(createProjectionDTO.getDate());

        var proj = new Projection();
        proj.setDate(createProjectionDTO.getDate());
        proj.setStartTime(createProjectionDTO.getStartTime());

        var hall = hallService.findHallByName(createProjectionDTO.getName());
        proj.setHall(hall);
        Integer numOfSeats = hall.getNumberOfSeats();

        Movie movie = movieService.getMovieByName(createProjectionDTO.getMovieName());
        proj.setMovie(movie);

        List<Seat> seatList = createSeatList(numOfSeats, proj);
        proj.setSeatList(seatList);

        Projection projCreated = projectionRepository.save(proj);
        log.info(String.format("Projection %s has been created.", proj.getProjectionId()));
        return projCreated;

    }

    /** update projection*/
    public Projection updateProjection(int id, CreateOrUpdateProjectionDTO updateProjectionDTO) {

        if (updateProjectionDTO == null) {
            throw new InvalidDataException("Projection data cannot be null");
        }
        var proj = projectionRepository.findById(id);
        if (proj == null) {
            throw new ObjectNotFoundException(String.format("The Projection with Id = %s doesn't exists", id));
        }

        timeValidator.checkTime(updateProjectionDTO.getStartTime());
        dateValidator.checkDate(updateProjectionDTO.getDate());

        proj.setDate(updateProjectionDTO.getDate());
        proj.setStartTime(updateProjectionDTO.getStartTime());

        var oldHall = proj.getHall();
        var oldSeatNum = oldHall.getNumberOfSeats();
        var hall = hallService.findHallByName(updateProjectionDTO.getName());
        proj.setHall(hall);

        var numOfSeats = hall.getNumberOfSeats();
        var oldSeatList = proj.getSeatList();
        var seatList = updateSeatList(oldSeatNum, numOfSeats, proj, oldSeatList);

        proj.setSeatList(seatList);

        var projUpdate = projectionRepository.save(proj);

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

    public List<Seat> updateSeatList(int numOld, int numNew, Projection proj, List<Seat> seatList){
        if (numNew >numOld){
            IntStream.range(numOld+1, numNew)
                    .forEach(index ->{
                        var seat = new Seat();
                        seat.setSeatNumber(index);
                        seat.setProjection(proj);
                        seatList.add(seat);
                    });
        }
        if(numNew<numOld){
            IntStream.range(numNew, numOld)
                    .forEach(seatList::remove);
        }
        return seatList;
    }

    /** get projections of a movie*/
    public Collection<Projection> getProjectionsByMovie(String name){
        if(name == null){
            throw  new InvalidDataException("Movie name cannot be null");
        }
        return  projectionRepository.listOfProjectionByMovie(name);
    }

}
