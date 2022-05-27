package com.netcrecker;

import com.netcrecker.services.UsersServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootStarter {

/*

    private static final Logger LOGGER = LoggerFactory.getLogger(SpringBootStarter.class);
*/

    @Autowired
    private UsersServiceImpl service;

    public static void main(String[] args) {
        SpringApplication.run(SpringBootStarter.class, args);
    }

   /* @Override
    public void run(String... args) throws Exception {
        LOGGER.info("save");
        service.save(new User("name", "surname",  "patronymic", 55, 55, "email", "workplace"));

        LOGGER.info("find all");
        service.findAll().forEach(item -> LOGGER.info(item.toString()));
    }*/
}
