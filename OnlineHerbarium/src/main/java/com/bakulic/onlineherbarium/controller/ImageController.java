package com.bakulic.onlineherbarium.controller;

import com.bakulic.onlineherbarium.model.Image;
import com.bakulic.onlineherbarium.model.User;
import com.bakulic.onlineherbarium.model.UserList;
import com.bakulic.onlineherbarium.service.ImageService;
import com.bakulic.onlineherbarium.service.UserListService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;
import java.util.List;


@AllArgsConstructor
@Controller
@RequestMapping(value = "/image")
public class ImageController {

    @Autowired
    private final ImageService imageService;

//    @Autowired
//    private final UserListService userListService;

    @ModelAttribute("image")
    public Image image() {
        return new Image();
    }

/*
    @PostMapping("/upload-image")
    public int uploadImage(@RequestParam("file") MultipartFile file, UserList userList) throws Exception {
        return imageService.save(file.getBytes(), userList);

    }
*/
    @GetMapping
    public String getUserImages(@PathVariable("id") int id, Model model){
        Collection<Image> imageList = imageService.getAllImagesByUserList(id);
        model.addAttribute("images", imageList);

        return "image-gallery";
    }
}
