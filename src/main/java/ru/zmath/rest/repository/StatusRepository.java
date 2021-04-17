package ru.zmath.rest.repository;

import org.springframework.data.repository.CrudRepository;
import ru.zmath.rest.model.Status;

public interface StatusRepository extends CrudRepository<Status, Integer> {
}