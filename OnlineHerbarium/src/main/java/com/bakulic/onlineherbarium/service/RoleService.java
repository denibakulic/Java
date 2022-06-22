package com.bakulic.onlineherbarium.service;

import com.bakulic.onlineherbarium.exceptions.InvalidDataException;
import com.bakulic.onlineherbarium.exceptions.ObjectNotFoundException;
import com.bakulic.onlineherbarium.model.Role;
import com.bakulic.onlineherbarium.model.dto.CreateOrUpdateRoleDTO;
import com.bakulic.onlineherbarium.repository.RoleRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Data
@Service
public class RoleService {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(RoleService.class);

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    public RoleRepository getRoleRepositoryRepository(){
        return roleRepository;
    }

    /**list of all roles*/
    public List<Role> getAllRoles(){
        return roleRepository.findAll();
    }

    /** create role **/
    public Role createRole(CreateOrUpdateRoleDTO createRoleDTO){
        if(createRoleDTO == null){
            throw new InvalidDataException("Role cannot be null");
        }
        Role role = new Role();
        role.setName(createRoleDTO.getName());
        Role roleCreated = roleRepository.save(role);
        log.info(String.format("Role %s has been created.", role.getName()));
        return roleCreated;

    }

    /**update role*/
    public Role updateRole(int id, CreateOrUpdateRoleDTO updateRoleDTO){

        if (updateRoleDTO == null) {
            throw new InvalidDataException("Role data cannot be null");
        }
        Role role = roleRepository.findById(id);
        if (role ==null) {
            throw new ObjectNotFoundException(String.format("The role with Id = %s doesn't exists", id));
        }

        role.setName(updateRoleDTO.getName());


        Role roleUpdate = roleRepository.save(role);
        log.info(String.format("Hall %s has been updated.", role.getName()));
        return roleUpdate;
    }

    /**list of all roles by user*/
    public Collection<Role> getAllRolesByUser(int id){
        return roleRepository.listOfAllRolesByUser(id);
    }
}
