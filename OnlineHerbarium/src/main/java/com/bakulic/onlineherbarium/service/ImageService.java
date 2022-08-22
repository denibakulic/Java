package com.bakulic.onlineherbarium.service;

import com.bakulic.onlineherbarium.model.Image;
import com.bakulic.onlineherbarium.model.UserList;
import com.bakulic.onlineherbarium.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class ImageService {

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private UserListService userListService;


    @Autowired
    public ImageRepository getImageRepository() {return imageRepository;}


    /** list of all images by userlist*/
    public Collection<Image> getAllImagesByUserList(int id){
        return imageRepository.listOfAllImagesByUserList(id);
    }

}
