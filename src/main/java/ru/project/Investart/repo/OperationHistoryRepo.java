package ru.project.Investart.repo;

import org.springframework.data.repository.CrudRepository;
import ru.project.Investart.entity.Investor;
import ru.project.Investart.entity.OperationHistory;
import ru.project.Investart.entity.Startup;

import java.util.List;

public interface OperationHistoryRepo extends CrudRepository<OperationHistory,Long> {
    List<OperationHistory> findOperationHistoriesByInvestor(Investor investor);

}
