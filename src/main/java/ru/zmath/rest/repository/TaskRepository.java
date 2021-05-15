package ru.zmath.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.zmath.rest.model.Task;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Integer> {

    List<Task> findAll();
}
