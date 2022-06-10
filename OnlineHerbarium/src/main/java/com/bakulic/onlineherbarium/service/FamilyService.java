package com.bakulic.onlineherbarium.service;

import com.bakulic.onlineherbarium.exceptions.*;
import com.bakulic.onlineherbarium.model.Family;
import com.bakulic.onlineherbarium.model.dto.CreateOrUpdateFamilyDTO;
import com.bakulic.onlineherbarium.repository.FamilyRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class FamilyService {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(FamilyService.class);

    @Autowired
    private FamilyRepository familyRepository;

    @Autowired
    public FamilyRepository getFamilyRepositoryRepository(){
        return familyRepository;
    }


    /** create family **/
    public Family createFamily(CreateOrUpdateFamilyDTO createFamilyDTO){
        if(createFamilyDTO == null){
            throw new InvalidDataException("Role cannot be null");
        }
        Family family = new Family();

        Family familyCreated = familyRepository.save(family);
        log.info(String.format("Family %s has been created.", family.getName()));
        return familyCreated;

    }

    /**update family*/
    public Family updateFamily(int id, CreateOrUpdateFamilyDTO updateFamilyDTO){

        if (updateFamilyDTO == null) {
            throw new InvalidDataException("Role data cannot be null");
        }
        Family family = familyRepository.findById(id);
        if (family ==null) {
            throw new ObjectNotFoundException(String.format("The role with Id = %s doesn't exists", id));
        }

        family.setName(updateFamilyDTO.getName());


        Family familyUpdated = familyRepository.save(family);
        log.info(String.format("Hall %s has been updated.", family.getName()));
        return familyUpdated;
    }

    /**list of all families*/
    public List<Family> getAllFamilies(){
        return familyRepository.findAll();
    }

}
