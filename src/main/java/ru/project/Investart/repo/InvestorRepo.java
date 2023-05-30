package ru.project.Investart.repo;

import org.springframework.data.repository.CrudRepository;
import ru.project.Investart.controller.InvestorController;
import ru.project.Investart.entity.Investor;
import ru.project.Investart.entity.User;

public interface InvestorRepo extends CrudRepository<Investor,Long> {
    Investor findInvestorByUser(User user);
}
