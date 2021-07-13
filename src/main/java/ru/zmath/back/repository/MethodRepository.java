package ru.zmath.back.repository;

import org.springframework.data.repository.CrudRepository;
import ru.zmath.back.model.Method;

import java.util.List;

public interface MethodRepository extends CrudRepository<Method, Integer> {
    List<Method> findAll();
}
