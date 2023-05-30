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

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
@Slf4j
@CrossOrigin
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
        User tryUser = userRepo.findUserByUsername(userLogin.getUsername());
        if(tryUser == null) return null;
        if(!userLogin.getPassword().equals(tryUser.getPassword())) return null;
        return tryUser;
    }

    @GetMapping("/user/{id}")
    public User getUserByID(@PathVariable Long id){
        var user = userRepo.findById(id);
        if (user.isPresent()) {
            return user.get();
        } else {
            return null;
        }

    }

    @GetMapping("/users")
    public List<User> getAllUsers(){
        return userRepo.findAll();
    }

    @GetMapping("/investors")
    public List<User> getInvestors(){
        return userRepo.findAll().stream().filter(u -> u.getRole().getId()==1).collect(Collectors.toList());
    }

    @GetMapping("/devByUID/{id}")
    public List<DevTeam> getDevByUID(@PathVariable Long id){
        return devTeamRepo.findAll().stream().filter(d -> d.getUser().getId()==id).collect(Collectors.toList());
    }

}