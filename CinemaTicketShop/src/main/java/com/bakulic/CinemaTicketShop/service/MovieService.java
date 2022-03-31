package com.bakulic.CinemaTicketShop.service;

import com.bakulic.CinemaTicketShop.model.Movie;
import com.bakulic.CinemaTicketShop.repository.MovieRepository;
import com.bakulic.CinemaTicketShop.model.dto.requests.CreateOrUpdateMovieDTO;
import com.bakulic.CinemaTicketShop.service.validation.*;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import com.bakulic.CinemaTicketShop.exceptions.*;

@AllArgsConstructor
@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    public MovieRepository getMovieRepository(){
        return movieRepository;
    }

    private LengthValidator lengthValidator;
    public MovieService(){lengthValidator = new LengthValidator();}

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(UserService.class);

    /**list of all users*/
    public List<Movie> getAllMovies() {

        return movieRepository.findAll();
    }

        /** create movie*/
        public Movie createMovie (CreateOrUpdateMovieDTO createMovieDTO){
            if (createMovieDTO == null) {
                throw new InvalidDataException("Movie data cannot be null");
            }

            lengthValidator.checkLength(createMovieDTO.getLength());

            // create the movie
            Movie movie = new Movie();
            movie.setName(createMovieDTO.getName());
            movie.setDescription(createMovieDTO.getDescription());
            movie.setLength(createMovieDTO.getLength());
            movie.setProjectionList(createMovieDTO.getProjectionList());
            movie.setPicture(createMovieDTO.getPicture());


            Movie movieCreated = movieRepository.save(movie);

            log.info(String.format("Movie %s has been created.", movieCreated.getName()));
            return movieCreated;
        }

        /** update movie*/
        public Movie updateMovie (int id, CreateOrUpdateMovieDTO updateMovieDTO){

            if (updateMovieDTO == null) {
                throw new InvalidDataException("Movie data cannot be null");
            }

            Movie movie = movieRepository.findById(id);

            movie.setName(updateMovieDTO.getName());
            movie.setDescription(updateMovieDTO.getDescription());
            movie.setLength(updateMovieDTO.getLength());
            movie.setProjectionList(updateMovieDTO.getProjectionList());
            movie.setPicture(updateMovieDTO.getPicture());

            Movie movieUpdated = movieRepository.save(movie);
            log.info(String.format("Movie %s has been updated.", movie.getName()));
            return movieUpdated;
        }


        /**get movie by name*/
        public Movie getMovieByName (String name){
            if (name == null) {
                throw new InvalidDataException("Name cannot be null");
            }
            return movieRepository.getByName(name);
        }


    }
