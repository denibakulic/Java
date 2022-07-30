package com.bakulic.onlineherbarium.controller;

import com.bakulic.onlineherbarium.model.Image;
import com.bakulic.onlineherbarium.service.HerbariumService;
import com.bakulic.onlineherbarium.service.ImageService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;



@AllArgsConstructor
@Controller
@RequestMapping(value = "/image")
public class ImageController {

    @Autowired
    private final ImageService imageService;

    @PostMapping
    public String uploadImage(@RequestParam("file") MultipartFile file) throws Exception {

        Image image = new Image();
        image.setContent(file.getBytes());
        //UserList us =
       //image.setUserList(); kako spremit kojoj listi pripada
        imageService.getImageRepository().save(image);

        return "redirect:/userList/list/{id}"; //ne znan jel radi
    }
    /* dohvacanje slika korisnika
    @GetMapping
    public String getUserImages()*/
}
