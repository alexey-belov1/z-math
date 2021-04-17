package ru.zmath.rest.service;

import org.springframework.stereotype.Service;
import ru.zmath.rest.model.Review;
import ru.zmath.rest.model.Task;
import ru.zmath.rest.repository.ReviewRepository;

import java.util.List;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public ReviewService(final ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public List<Review> findAll() {
        return this.reviewRepository.findAll();
    }

    public Review save(Review review) {
        return this.reviewRepository.save(review);
    }
}
