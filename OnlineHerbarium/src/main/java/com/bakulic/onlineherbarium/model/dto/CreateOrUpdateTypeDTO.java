package com.bakulic.onlineherbarium.model.dto;

import com.bakulic.onlineherbarium.model.HerbariumOrList;
import lombok.Data;

import java.util.List;

@Data
public class CreateOrUpdateTypeDTO {

    private String name;
    private List<HerbariumOrList> herbariumOrLists; //vjv trebat izmjene

}
