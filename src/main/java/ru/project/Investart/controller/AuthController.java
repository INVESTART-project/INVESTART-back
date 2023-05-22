package ru.project.Investart.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import ru.project.Investart.entity.User;

import ru.project.Investart.forms.UserService;
import ru.project.Investart.repo.UserRepo;

import java.util.List;

@RestController
@RequestMapping("/auth")
@Slf4j
public class AuthController {

    private UserRepo userRepo;
    private PasswordEncoder passwordEncoder;

    public AuthController(
            UserRepo userRepo,
            PasswordEncoder passwordEncoder
    ) {
        this.passwordEncoder = passwordEncoder;
        this.userRepo = userRepo;
    }

    @PostMapping(path = "/register",consumes = "application/json", produces = "application/json")
    public User registration(@RequestBody UserService userService) {
        return userRepo.save(userService.toUser(passwordEncoder));
    }

    @PostMapping(path = "/login", consumes = "application/json", produces = "application/json")
    public User signIn(@RequestBody UserService userService) {
        return userRepo.save(userService.toUser(passwordEncoder));
    }



    @GetMapping
    public List<User> getAll(){
        return userRepo.findAll();
    }

//    @PostMapping("/login")
//    public List<User> processLogin(@RequestBody UserWrapper userWrapper) {
//
//        if (!userRepo.existsUserByUsername(userWrapper.getUsername())) {
//            try {
//                userRepo.save(registrationForm.toUser(passwordEncoder));
//            } catch (Exception e) {
//                log.info("cannot save");
//            }
//
//            log.info("Registered new user" + registrationForm.toUser(passwordEncoder).toString());
//        }
//        return userRepo.findAll();
//    }
}