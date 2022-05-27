package com.netcrecker.controller;

import com.netcrecker.model.User;
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
        BufferedReader reader = new BufferedReader(new
                InputStreamReader(file.getInputStream(), "UTF-8"));

        // считываем построчно
        String line = null;
        Scanner scanner = null;
        List<User> users = new ArrayList<>();
        while ((line = reader.readLine()) != null) {
            User user = new User();
            scanner = new Scanner(line);
            scanner.useDelimiter(",");
            while (scanner.hasNext()) {
                String data = scanner.next();
                user.setName(data);
                String data1 = scanner.next();
                user.setSurname(data1);
                String data2 = scanner.next();
                user.setPatronymic(data2);
                String data3 = scanner.next();
                user.setAge(Integer.parseInt(data3));
                String data4 = scanner.next();
                user.setSalary(Integer.parseInt(data4));
                String data5 = scanner.next();
                user.setEmail(data5);
                String data6 = scanner.next();
                user.setWorkplace(data6);
            }
            users.add(user);
        }
        userService.save(users.get(0));
        //закрываем наш ридер
        reader.close();

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