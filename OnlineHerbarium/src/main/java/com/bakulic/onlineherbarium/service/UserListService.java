package com.bakulic.onlineherbarium.service;


import com.bakulic.onlineherbarium.exceptions.*;
import com.bakulic.onlineherbarium.model.Plant;
import com.bakulic.onlineherbarium.model.User;
import com.bakulic.onlineherbarium.model.UserList;
import com.bakulic.onlineherbarium.model.dto.CreateOrUpdateUserListDTO;
import com.bakulic.onlineherbarium.repository.PlantRepository;
import com.bakulic.onlineherbarium.repository.UserListRepository;
import com.bakulic.onlineherbarium.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.List;

@AllArgsConstructor
@Service
@Data
public class UserListService {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(UserList.class);

    @Autowired
    private UserListRepository userListRepository;

    @Autowired
    private PlantRepository plantRepository;

    @Autowired
    private UserRepository userRepository;


    /** create UserList **/
    public UserList createUserList(CreateOrUpdateUserListDTO createListDTO, int id) {
        if (createListDTO == null) {
            throw new InvalidDataException("List cannot be null");
        }
        User user = userRepository.findById(id);
        UserList newList = new UserList();
        newList.setTitle(createListDTO.getTitle());
        newList.setDescription(createListDTO.getDescription());
        newList.setUser(user);

        DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        newList.setDate(now);

        UserList listCreated = userListRepository.save(newList);
        log.info(String.format("List %s has been created.", listCreated.getTitle()));
        return listCreated;

    }

    /** update UserList*/
    public UserList updateUserList(int id, CreateOrUpdateUserListDTO updateListDTO){

        if (updateListDTO == null) {
            throw new InvalidDataException("Update data cannot be null");
        }
        UserList list = userListRepository.findById(id);
        if (list ==null) {
            throw new ObjectNotFoundException(String.format("The list with Id = %s doesn't exists", id));
        }

        list.setTitle(updateListDTO.getTitle());
        list.setDescription(updateListDTO.getDescription());
        DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        list.setDate(now);

        UserList listUpdated = userListRepository.save(list);
        log.info(String.format("List %s has been updated.", list.getTitle()));
        return listUpdated;
    }

    /** add plants to list*/
    public void addPlantsToList(int listId, int plantId){
        UserList ul = userListRepository.findById(listId);
        Plant plant = plantRepository.findById(plantId);
        ul.getPlants().add(plant);
        userListRepository.save(ul);
    }


    /** remove plant from list*/
    public void removePlantFromList(int listId, int plantId){
        UserList list = userListRepository.findById(listId);
        Plant plant = plantRepository.findById(plantId);
        list.getPlants().remove(plant);
        userListRepository.save(list);
    }



    /**list of all lists by user*/
    public Collection<UserList> getAllListsByUser(int id){return userListRepository.listOfAllUserListByUser(id);
    }


}
