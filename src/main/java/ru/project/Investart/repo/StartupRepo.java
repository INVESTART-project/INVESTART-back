package ru.project.Investart.repo;

import org.springframework.data.repository.CrudRepository;
import ru.project.Investart.entity.Startup;

import java.util.List;

public interface StartupRepo extends CrudRepository<Startup,Long> {

    Startup findStartupById(Long id);
    List<Startup> findAll();
}
