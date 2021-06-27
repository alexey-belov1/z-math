package ru.zmath.rest.repository;

import org.springframework.data.repository.CrudRepository;
import ru.zmath.rest.model.Status;

import java.util.List;

public interface StatusRepository extends CrudRepository<Status, Integer> {
    List<Status> findAll();
}
