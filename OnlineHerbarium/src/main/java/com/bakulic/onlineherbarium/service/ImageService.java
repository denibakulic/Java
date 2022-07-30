package com.bakulic.onlineherbarium.service;

import com.bakulic.onlineherbarium.model.Image;
import com.bakulic.onlineherbarium.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.List;

public class ImageService {

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    public ImageRepository getImageRepository() {return imageRepository;}

    //kod s primjera

    /** list of all images*/
    public List<Image> getAllImages() {return imageRepository.findAll();}

    /** list of all images by userlist*/
    public Collection<Image> getAllImagesByUserList(int id){
        return imageRepository.listOfAllImagesByUserList(id);
    }

    /** list of all images by user*/
    public Collection<Image> getAllImagesByUser(int id){
        return imageRepository.listOfAllImagesByUser(id);
    }
}
