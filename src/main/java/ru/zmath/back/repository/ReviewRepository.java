package ru.zmath.back.repository;

import org.springframework.data.repository.CrudRepository;
import ru.zmath.back.model.Review;

import java.util.List;

public interface ReviewRepository extends CrudRepository<Review, Integer> {

    List<Review> findAll();

    Integer deleteReviewById(Integer id);
}
