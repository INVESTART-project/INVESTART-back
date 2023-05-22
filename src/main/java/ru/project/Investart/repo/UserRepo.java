package ru.project.Investart.repo;

import org.springframework.data.repository.CrudRepository;
import ru.project.Investart.entity.User;

import java.util.List;

public interface UserRepo extends CrudRepository<User,Long> {
    public User findUserByUsername(String username);
    public List<User> findAll();

}
