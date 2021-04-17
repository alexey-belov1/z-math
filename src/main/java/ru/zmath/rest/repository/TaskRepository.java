package ru.zmath.rest.repository;

import org.springframework.data.repository.CrudRepository;
import ru.zmath.rest.model.Subject;
import ru.zmath.rest.model.Task;

import java.util.List;

public interface TaskRepository extends CrudRepository<Task, Integer> {

    List<Task> findAll();
}