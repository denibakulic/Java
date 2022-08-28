package com.bakulic.onlineherbarium.controller;

import com.bakulic.onlineherbarium.model.Image;
import com.bakulic.onlineherbarium.model.UserList;
import com.bakulic.onlineherbarium.service.ImageService;
import com.bakulic.onlineherbarium.service.UserListService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.*;
import java.util.Collection;



@AllArgsConstructor
@Controller
@RequestMapping(value = "/image")
public class ImageController {

    @Autowired
    private final ImageService imageService;



    @Autowired
    private final UserListService userListService;


    @ModelAttribute("image")
    public Image image() {
        return new Image();
    }

    private final String UPLOAD_DIR = "C:/Zavrsni/Java/OnlineHerbarium/src/main/resources/static/user_uploads" ;


    @GetMapping("/upload/{id}")
    public String getUploadForm(Model model, @PathVariable ("id") int id){
        UserList ul=userListService.getUserListRepository().findById(id);
        model.addAttribute("list", ul);
        return "image-upload";
    }
    @PostMapping("/upload/{id}")
    public String uploadFile(@RequestParam("file") MultipartFile file, RedirectAttributes attributes, @PathVariable ("id") int id) {

        Image img = new Image();
        UserList ul = userListService.getUserListRepository().findById(id);
        img.setUserList(ul);

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        img.setLocation("/user_uploads" + fileName);


        try {
            Path path = Paths.get(UPLOAD_DIR + fileName);
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
        imageService.getImageRepository().save(img);
        attributes.addFlashAttribute("message", "You successfully uploaded " + fileName + '!');
        return "redirect:/userlist/list/{id}";
    }


    @GetMapping("/gallery/{id}")
    public String getUserImages(@PathVariable("id") int id, Model model){
        Collection<Image> imageList = imageService.getAllImagesByUserList(id);
        UserList userList = userListService.getUserListRepository().findById(id);
        model.addAttribute("list", userList);
        model.addAttribute("images", imageList);

        return "image-gallery";
    }

    @GetMapping("/delete/{imgId}")
    public  String deleteImage(@PathVariable int imgId){
        imageService.getImageRepository().deleteById(imgId);
        return "redirect:/";
    }
}
