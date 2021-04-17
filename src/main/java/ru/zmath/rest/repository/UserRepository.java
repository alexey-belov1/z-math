package ru.zmath.rest.repository;

import org.springframework.data.repository.CrudRepository;
import ru.zmath.rest.model.User;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Integer> {

    List<User> findAll();

    List<User> findByLogin(String name);

    Integer deleteUserById(Integer id);
}