package ru.zmath.rest.repository;

import org.springframework.data.repository.CrudRepository;
import ru.zmath.rest.model.Method;

import java.util.List;

public interface MethodRepository extends CrudRepository<Method, Integer> {
    List<Method> findAll();
}
