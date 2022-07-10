package com.bakulic.onlineherbarium.service;

import com.bakulic.onlineherbarium.exceptions.InvalidDataException;
import com.bakulic.onlineherbarium.exceptions.ObjectNotFoundException;
import com.bakulic.onlineherbarium.model.Herbarium;
import com.bakulic.onlineherbarium.model.Plant;
import com.bakulic.onlineherbarium.model.UserList;
import com.bakulic.onlineherbarium.model.dto.CreateOrUpdateUserListDTO;
import com.bakulic.onlineherbarium.repository.PlantRepository;
import com.bakulic.onlineherbarium.repository.UserListRepository;
import com.bakulic.onlineherbarium.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserListService {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(HerbariumService.class);

    @Autowired
    private UserListRepository userListRepository;

    @Autowired
    public UserListRepository getUserListRepository(){
        return userListRepository;
    }

    @Autowired
    private PlantRepository plantRepository;

    @Autowired
    private PlantRepository getPlantRepository(){return plantRepository;}

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRepository getUserRepository(){return userRepository;}

    /**
     * list of all users
     */
    public List<UserList> getAllUserLists() {
        return userListRepository.findAll();
    }


    /** create UserList **/
    public UserList createUserList(CreateOrUpdateUserListDTO createListDTO) {
        if (createListDTO == null) {
            throw new InvalidDataException("List cannot be null");
        }
        UserList newList = new UserList() {
        };
        newList.setTitle(createListDTO.getTitle());
        newList.setDescription(createListDTO.getDescription());

        DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        newList.setDate(now);
        newList.setUser(userRepository.findById(createListDTO.getUserId()));

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

        //ConnectData(list);
        UserList listUpdated = userListRepository.save(list);
        log.info(String.format("List %s has been updated.", list.getTitle()));
        return listUpdated;
    }

    /** fill connection data*//*
    private void getPlantsfromUserList(int id) {
        UserList list = getUserListRepository().findById(id);
        List<Plant> userPlants
        List<Plant> allPlants = getPlantRepository().findAll();
        list.getPlants() //kako u frontendu prikazat
                .addAll(list
                        .getPlants()
                        .stream()
                        .map(p ->{
                            Plant pp = getPlantRepository().findById(p.getPlantId());
                            pp.getLists().add(list);
                            return pp;
                        }).collect(Collectors.toList()));


    }
*/
    /**list of all lists by user*/
    public Collection<UserList> getAllListsByUser(int id){return userListRepository.listOfAllUserListByUser(id);
    }

}
