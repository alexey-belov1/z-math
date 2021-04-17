package ru.zmath.rest.repository;

import org.springframework.data.repository.CrudRepository;
import ru.zmath.rest.model.Solve;

public interface SolveRepository extends CrudRepository<Solve, Integer> {
}