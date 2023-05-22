package ru.project.Investart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.project.Investart.entity.User;
import ru.project.Investart.repo.UserRepo;

import java.util.List;

@RestController
@RequestMapping("/api")
public class HelloController {

    final UserRepo userRepo;


    public HelloController(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @GetMapping("/users")
    public List<User> getAll(){
        return userRepo.findAll();
    }
}
