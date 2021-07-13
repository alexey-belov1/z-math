package ru.zmath.back.repository;

import org.springframework.data.repository.CrudRepository;
import ru.zmath.back.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Integer> {

    List<User> findAll();

    List<User> findByLogin(String name);

    Optional<User> findDistinctByLogin(String name);

    Optional<User> findDistinctByEmail(String email);

    Integer deleteUserById(Integer id);
}
