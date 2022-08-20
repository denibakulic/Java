package com.bakulic.onlineherbarium.service;

import com.bakulic.onlineherbarium.model.Image;
import com.bakulic.onlineherbarium.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class ImageService {

    @Autowired
    private ImageRepository imageRepository;

//    @Autowired
//    FileSystemRepository fileSystemRepository;

    @Autowired
    public ImageRepository getImageRepository() {return imageRepository;}

   /* public int save(byte[] bytes, UserList userList) throws Exception {
        String location = fileSystemRepository.save(bytes, userList);

        return imageRepository.save(new Image(userList, location))
                .getId();

    }*/

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
