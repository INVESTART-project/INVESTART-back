package ru.project.Investart.repo;

import org.springframework.data.repository.CrudRepository;
import ru.project.Investart.entity.User;

import java.util.List;

public interface UserRepo extends CrudRepository<User,Long> {
     User findUserByUsername(String username);
     List<User> findAll();

    boolean existsUserByUsername(String username);
}
