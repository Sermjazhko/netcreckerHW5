package com.netcrecker.services;

import com.netcrecker.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/*Это специальный тип классов, в котором реализуется некоторая бизнес логика приложения. Впоследствии,
благодаря этой аннотации Spring будет предоставлять нам экземпляр данного класса в местах, где это, нужно с помощью Dependency Injection.*/
@Service
public class UsersServiceImpl{

    @Autowired
    private UserService userService;

    // возвращаем список пользователей
    public List<User> findAll(){ return userService.findAll();}
    // ищем пользователя по id
    public List<User> findByNameAndSurname(String name, String surname){
        return userService.findByNameAndSurname(name,surname);
    }

    public void save(User user){
        userService.save(user);
    }

}
