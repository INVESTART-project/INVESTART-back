package ru.project.Investart.repo;

import org.springframework.data.repository.CrudRepository;
import ru.project.Investart.entity.DevTeam;
import ru.project.Investart.entity.User;

import java.util.List;

public interface DevTeamRepo extends CrudRepository<DevTeam, Long> {
    DevTeam findDevTeamById(Long Id);

    List<DevTeam> findAll();
}
