package ru.zmath.rest.repository;

import org.springframework.data.repository.CrudRepository;
import ru.zmath.rest.model.Subject;
import ru.zmath.rest.model.User;

import java.util.List;

public interface SubjectRepository extends CrudRepository<Subject, Integer> {

    List<Subject> findAll();
}