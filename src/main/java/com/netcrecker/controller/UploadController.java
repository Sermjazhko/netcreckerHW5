package com.netcrecker.controller;

import com.netcrecker.model.User;
import com.netcrecker.services.CSVFileHandler;
import com.netcrecker.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Controller
public class UploadController {

    private final UserService userService;
    public static final String CSV_UPLOAD = "src/main/resources/files/upload.csv";
    @Autowired
    public UploadController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/uploadFile")
    public String showUploadFileForm() {
        return "uploadFile";
    }

    @PostMapping("/uploadFile")
    public String upload(@RequestParam("file") MultipartFile file, Model model) throws IOException {
        if(file.isEmpty()){
            return "uploadFileIsEmpty";
        }

        List<User> users = CSVFileHandler.parse(file);
        userService.save(users.get(0));

        // Для файлового разделитель ещё кавычки
        /*Path path = Paths.get(CSV_UPLOAD);
        try{
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<User> users = parseCSVtoUsers(CSV_UPLOAD);
        writeUserToCSV(users.get(0));
      //  userService.save(users.get(0));
        deleteFile(path);
        model.addAttribute("file", file);*/
        return "successfulUpload";
    }

    public void deleteFile(Path path){
        try {
            Files.deleteIfExists(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}