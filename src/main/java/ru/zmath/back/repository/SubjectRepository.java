package ru.zmath.back.repository;

import org.springframework.data.repository.CrudRepository;
import ru.zmath.back.model.Subject;

import java.util.List;

public interface SubjectRepository extends CrudRepository<Subject, Integer> {

    List<Subject> findAll();
}
