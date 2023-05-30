package ru.project.Investart.controller;

import org.springframework.web.bind.annotation.*;
import ru.project.Investart.entity.Startup;
import ru.project.Investart.forms.StartupReq;
import ru.project.Investart.repo.DevTeamRepo;
import ru.project.Investart.repo.StartupRepo;
import ru.project.Investart.repo.UserRepo;

import java.util.List;

@RestController
@RequestMapping("/startup")
public class StartupController {

    final UserRepo userRepo;
    final StartupRepo startupRepo;
    final DevTeamRepo devTeamRepo;


    public StartupController(UserRepo userRepo, StartupRepo startupRepo, DevTeamRepo devTeamRepo) {
        this.userRepo = userRepo;
        this.startupRepo = startupRepo;
        this.devTeamRepo = devTeamRepo;
    }


    @PostMapping("/newProject")
    public List<Startup> newProject(@RequestBody StartupReq startupReq){
        Startup newStartup = new Startup();
        newStartup.setName(startupReq.getName());
        newStartup.setEndDate(startupReq.getEndDate());
        newStartup.setNeedMoney(startupReq.getNeedMoney());
        newStartup.setAuthor(devTeamRepo.findDevTeamById(startupReq.getAuthor_id()));
        startupRepo.save(newStartup);

        return startupRepo.findAll();
    }

    @GetMapping("/{id}")
    public Startup getAllStartups(@PathVariable Long id){
        return startupRepo.findStartupById(id);
    }

}
