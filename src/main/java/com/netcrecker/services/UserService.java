package com.netcrecker.services;

import com.netcrecker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserService extends JpaRepository<User, Integer> {

    // возвращаем список пользователей
    List<User> findAll();
    // ищем пользователя по id
    List<User> findByNameAndSurname(String name, String surname);

    // значит второй параметр name получаем так и поставляем в стринг
    @Query(value = "select * from users e where e.name= :name and e.surname= :surname", nativeQuery = true)
    List<User> retrieveByName(@Param("name") String name, @Param("surname") String surname);

    @Query(value = "select * from users e order by e.id DESC LIMIT 1", nativeQuery = true)
    User getLastUser();

}
