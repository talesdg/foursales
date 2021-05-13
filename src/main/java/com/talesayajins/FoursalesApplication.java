package com.talesayajins;

import com.talesayajins.entities.User;
import com.talesayajins.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class FoursalesApplication implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder crypt;

    public static void main(String[] args) {
        SpringApplication.run(FoursalesApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        User user = new User("admin",crypt.encode("admin"));
        if(userRepository.findByLogin("admin") == null){
            userRepository.save(user);
        }
    }

}
