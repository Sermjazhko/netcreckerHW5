package com.netcrecker.controller;

import com.netcrecker.model.User;
import com.netcrecker.services.CSVFileHandler;
import com.netcrecker.services.MailSenderService;
import com.netcrecker.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.util.StringUtils;

import java.util.List;

import static com.netcrecker.services.CSVFileHandler.findByFullName;
import static com.netcrecker.services.CSVFileHandler.parseCSVtoUsers;
//мб RestController

@Controller
public class UserController {

    private final UserService userService;
    @Autowired
    private MailSenderService sender;
//@Autowired — //говорит спрингу, что в этом месте необходимо внедрить зависимость.

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public String userForm(Model model) {
        model.addAttribute("user", new User());
        return "user";
    }

    @PostMapping("/user")
    public String userSubmit(@ModelAttribute User user) {
        userService.save(user);
        //CSVFileHandler.writeUserToCSV(user);
        return "successfulSubmit";
    }

    @GetMapping("/searchUser")
    public String showSearchForm(Model model) {
        model.addAttribute("user", new User());
        return "searchUser";
    }

    @PostMapping("/searchUser")
    public String getUserData(User user, Model model) {
        List<User> list= userService.retrieveByName(user.getName(), user.getSurname());
        /*List<User> users = parseCSVtoUsers(CSVFileHandler.SCV_USERS);
        int index = findByFullName(users, user.getName(), user.getSurname());*/
        if (list.size()!= 0){
             model.addAttribute("user", list.get(0));
            return "found";}
       /* if(index >= 0){
            model.addAttribute("user", users.get(index));
            return "found";
        }*/
        return "notFound";
    }

    @RequestMapping(value="/sendMsg")
    public String sendMsgToUser() {
        // смотрим только первого пользователя
        //List<User> users = parseCSVtoUsers(CSVFileHandler.SCV_USERS);
        //User user = users.get(users.size()-1);
        User user= userService.getLastUser();

        if (!StringUtils.isEmpty(user.getEmail())) {
            sendMessage(user);
            return "successfulSendMsg";
        } else {
            return "emailNotFound";
        }
    }

    public void sendMessage(User user){
        String message = String.format("Добрый день, %s! Вы зарегестрированы в системе.", user.getName());
        sender.sendMessage(user.getEmail(), "Сообщение о регистрации", message);
    }
}



