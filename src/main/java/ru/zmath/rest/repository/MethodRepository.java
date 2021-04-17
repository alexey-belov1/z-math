package ru.zmath.rest.repository;

import org.springframework.data.repository.CrudRepository;
import ru.zmath.rest.model.Method;

public interface MethodRepository extends CrudRepository<Method, Integer> {
}