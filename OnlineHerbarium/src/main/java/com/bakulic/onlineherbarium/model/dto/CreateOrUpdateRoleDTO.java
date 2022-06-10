package com.bakulic.onlineherbarium.model.dto;

import com.bakulic.onlineherbarium.model.User;
import lombok.Data;

import java.util.List;

@Data
public class CreateOrUpdateRoleDTO {

    private String name;
    private List<User> userList; //vjv trebat izmjene
}
