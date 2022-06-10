package com.bakulic.onlineherbarium.service;

import com.bakulic.onlineherbarium.exceptions.InvalidDataException;
import com.bakulic.onlineherbarium.exceptions.ObjectNotFoundException;
import com.bakulic.onlineherbarium.model.HerbariumOrList;
import com.bakulic.onlineherbarium.model.dto.CreateOrUpdateHerbariumDTO;
import com.bakulic.onlineherbarium.repository.HerbariumOrListRepository;
import com.bakulic.onlineherbarium.repository.TypeRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
@Data
@Service
public class HerbariumOrListService {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(HerbariumOrListService.class);

    @Autowired
    private HerbariumOrListRepository herbariumOrListRepository;

    @Autowired
    public HerbariumOrListRepository getHerbariumOrListRepository(){
        return herbariumOrListRepository;
    }

    @Autowired
    private TypeRepository typeRepository;

    @Autowired
    public TypeRepository getTypeRepository(){
        return typeRepository;
    }


    /** create Herbarium **/
    public HerbariumOrList createHerbarium(CreateOrUpdateHerbariumDTO createHerbariumDTO){
        if(createHerbariumDTO == null){
            throw new InvalidDataException("Herbarium cannot be null");
        }
        HerbariumOrList newHerbarium = new HerbariumOrList();
        newHerbarium.setTitle(createHerbariumDTO.getTitle());
        newHerbarium.setDescription(createHerbariumDTO.getDescription());
        newHerbarium.setDate(createHerbariumDTO.getDate());
        newHerbarium.setType(typeRepository.findByTypeId(1));
        newHerbarium.setPicture(createHerbariumDTO.getPicture()); //treba vidit kako napravit i spremat

        HerbariumOrList herbariumCreated = herbariumOrListRepository.save(newHerbarium);
        log.info(String.format("Herbarium %s has been created.", herbariumCreated.getTitle()));
        return herbariumCreated;

    }

    /** create List **/
    public HerbariumOrList createList(CreateOrUpdateHerbariumDTO createListDTO){
        if(createListDTO == null){
            throw new InvalidDataException("Herbarium cannot be null");
        }
        HerbariumOrList newList = new HerbariumOrList();
        newList.setTitle(createListDTO.getTitle());
        newList.setDescription(createListDTO.getDescription());
        newList.setDate(createListDTO.getDate());
        newList.setType(typeRepository.findByTypeId(2));

        HerbariumOrList listCreated = herbariumOrListRepository.save(newList);
        log.info(String.format("List %s has been created.", listCreated.getTitle()));
        return listCreated;

    }

    /**update Herbarium*/
    public HerbariumOrList updateHerbarium(int id, CreateOrUpdateHerbariumDTO updateHerbariumDTO){

        if (updateHerbariumDTO == null) {
            throw new InvalidDataException("Update data cannot be null");
        }
        HerbariumOrList herbarium = herbariumOrListRepository.findById(id);
        if (herbarium ==null) {
            throw new ObjectNotFoundException(String.format("The herbarium with Id = %s doesn't exists", id));
        }

        herbarium.setTitle(updateHerbariumDTO.getTitle());
        herbarium.setDescription(updateHerbariumDTO.getDescription());
        herbarium.setDate(updateHerbariumDTO.getDate());
        herbarium.setPicture(updateHerbariumDTO.getPicture());


        HerbariumOrList herbariumUpdated = herbariumOrListRepository.save(herbarium);
        log.info(String.format("Herbarium %s has been updated.", herbarium.getTitle()));
        return herbariumUpdated;
    }

    /**update Herbarium*/
    public HerbariumOrList updateList(int id, CreateOrUpdateHerbariumDTO updateListDTO){

        if (updateListDTO == null) {
            throw new InvalidDataException("Update data cannot be null");
        }
        HerbariumOrList list = herbariumOrListRepository.findById(id);
        if (list ==null) {
            throw new ObjectNotFoundException(String.format("The list with Id = %s doesn't exists", id));
        }

        list.setTitle(updateListDTO.getTitle());
        list.setDescription(updateListDTO.getDescription());
        list.setDate(updateListDTO.getDate());

        HerbariumOrList listUpdated = herbariumOrListRepository.save(list);
        log.info(String.format("List %s has been updated.", list.getTitle()));
        return listUpdated;
    }

    /**list of all herbariums and lists*/
    public List<HerbariumOrList> getAllHerbariumOrLists(){
        return herbariumOrListRepository.findAll();
    }

    /**list of all herbariums*/
    public Collection<HerbariumOrList> getAllHerbariums(int id){
        return herbariumOrListRepository.listOfAllHerbariums(id);
    }

    /**list of all lists*/
    public Collection<HerbariumOrList> getAllLists(int id){
        return herbariumOrListRepository.listOfAllLists(id);
    }

}
