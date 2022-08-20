//package com.bakulic.onlineherbarium.repository;
//
//import com.bakulic.onlineherbarium.model.Image;
//import com.bakulic.onlineherbarium.model.UserList;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;
//
//import java.nio.file.*;
//import java.util.Date;
//
//@Repository
//public class FileSystemRepository{
//
//    String RESOURCES_DIR = FileSystemRepository.class.getResource("/resources/static/user_uploads")
//            .getPath();
//
//    public String save(byte[] content, UserList userList) throws Exception {
//        Path newFile = Paths.get(RESOURCES_DIR + new Date().getTime() + "-" + userList.getListId());
//        Files.createDirectories(newFile.getParent());
//
//        Files.write(newFile, content);
//
//        return newFile.toAbsolutePath()
//                .toString();
//    }
//}
