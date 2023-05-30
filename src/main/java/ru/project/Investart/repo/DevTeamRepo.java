package ru.project.Investart.repo;

import org.springframework.data.repository.CrudRepository;
import ru.project.Investart.entity.DevTeam;

public interface DevTeamRepo extends CrudRepository<DevTeam, Long> {
    DevTeam findDevTeamById(Long Id);
}
