package ru.project.Investart.controller;


import org.springframework.web.bind.annotation.*;
import ru.project.Investart.entity.Investor;
import ru.project.Investart.entity.OperationHistory;
import ru.project.Investart.entity.Startup;
import ru.project.Investart.forms.DonateReq;
import ru.project.Investart.repo.InvestorRepo;
import ru.project.Investart.repo.OperationHistoryRepo;
import ru.project.Investart.repo.StartupRepo;
import ru.project.Investart.repo.UserRepo;

import java.util.*;

@RestController
@RequestMapping("/invest")
@CrossOrigin
public class InvestorController {

    final InvestorRepo investorRepo;
    final UserRepo userRepo;
    final StartupRepo startupRepo;
    final OperationHistoryRepo operationHistoryRepo;

    public InvestorController(InvestorRepo investorRepo, UserRepo userRepo, StartupRepo startupRepo, OperationHistoryRepo operationHistoryRepo) {
        this.investorRepo = investorRepo;
        this.userRepo = userRepo;
        this.startupRepo = startupRepo;
        this.operationHistoryRepo = operationHistoryRepo;
    }

    @GetMapping("/{id}")
    public Investor getInv(@PathVariable Long id){
        return investorRepo.findInvestorByUser(userRepo.findUserById(id));
    }

    @PostMapping("/donate")
    public Startup donate(@RequestBody DonateReq donateReq){
        Startup startup = startupRepo.findStartupById(donateReq.getId_Startup());
        Investor investor = investorRepo.findInvestorByUser(userRepo.findUserById(donateReq.getId_User()));
        startup.setCurrentMoney(startup.getCurrentMoney() + donateReq.getTransMoney());
        OperationHistory operationHistory = new OperationHistory(investor,startup,new GregorianCalendar().getTime(),donateReq.getTransMoney());
        operationHistoryRepo.save(operationHistory);
        startupRepo.save(startup);
        return startup;
    }

    @GetMapping("/startups/{id}")
    public Set<Startup> getStartups(@PathVariable Long id){
        Investor investor = investorRepo.findInvestorByUser(userRepo.findUserById(id));
        List<OperationHistory> operationHistoryList = operationHistoryRepo.findOperationHistoriesByInvestor(investor);
        Set<Startup> startupSet = new HashSet<>();
        for (OperationHistory item: operationHistoryList) {
            startupSet.add(item.getStartup());
        }
        return startupSet;
    }
}
