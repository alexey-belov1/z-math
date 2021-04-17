package ru.zmath.rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.zmath.rest.model.Review;
import ru.zmath.rest.service.ReviewService;

import java.util.GregorianCalendar;
import java.util.List;

@RestController
@RequestMapping("/review")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(final ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/")
    public List<Review> findAll() {
        return this.reviewService.findAll();
    }

    @PostMapping("/")
    public ResponseEntity<Review> create(@RequestBody Review review) {
        review.setCreated(GregorianCalendar.getInstance());

        return new ResponseEntity<Review>(
                this.reviewService.save(review),
                HttpStatus.CREATED
        );
    }
}