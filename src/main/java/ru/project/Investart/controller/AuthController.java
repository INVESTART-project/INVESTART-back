package ru.project.Investart.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import ru.project.Investart.entity.DevTeam;
import ru.project.Investart.entity.Investor;
import ru.project.Investart.entity.User;

import ru.project.Investart.forms.UserLogin;
import ru.project.Investart.forms.UserService;
import ru.project.Investart.repo.DevTeamRepo;
import ru.project.Investart.repo.InvestorRepo;
import ru.project.Investart.repo.UserRepo;

@RestController
@RequestMapping("/auth")
@Slf4j
public class AuthController {


    final UserRepo userRepo;
    final DevTeamRepo devTeamRepo;
    final InvestorRepo investorRepo;

    public AuthController(UserRepo userRepo, DevTeamRepo devTeamRepo, InvestorRepo investorRepo) {
        this.userRepo = userRepo;
        this.devTeamRepo = devTeamRepo;
        this.investorRepo = investorRepo;
    }

    @PostMapping(path = "/register")
    public User newUser(@RequestBody UserService userService) {
        User newUser = userService.toUser();
        userRepo.save(newUser);
        if(newUser.getRole().getName().equals("DEVTEAM"))
        {
            DevTeam newDevTeam = new DevTeam(newUser.getUsername(), newUser.getUsername(), 5.00,newUser);
            devTeamRepo.save(newDevTeam);
        } else{
            Investor newInvestor = new Investor(5.00, newUser);
            investorRepo.save(newInvestor);
        }
        return newUser;
    }

    @PostMapping(path = "/login")
    public User signIn(@RequestBody UserLogin userLogin) {
        return userRepo.findUserByUsername(userLogin.getUsername());
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