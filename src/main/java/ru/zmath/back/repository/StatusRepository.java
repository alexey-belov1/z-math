package ru.zmath.back.repository;

import org.springframework.data.repository.CrudRepository;
import ru.zmath.back.model.Status;

import java.util.List;

public interface StatusRepository extends CrudRepository<Status, Integer> {
    List<Status> findAll();
}
