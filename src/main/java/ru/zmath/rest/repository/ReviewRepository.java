package ru.zmath.rest.repository;

import org.springframework.data.repository.CrudRepository;
import ru.zmath.rest.model.Review;

import java.util.List;

public interface ReviewRepository extends CrudRepository<Review, Integer> {

    List<Review> findAll();
}