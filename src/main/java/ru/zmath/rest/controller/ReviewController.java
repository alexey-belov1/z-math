package ru.zmath.rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.zmath.rest.controller.util.HeaderUtil;
import ru.zmath.rest.service.ReviewService;
import ru.zmath.rest.service.dto.ReviewDTO;

import java.util.List;

@RestController
@RequestMapping("/review")
public class ReviewController {

    private final String entity = "review";

    private final ReviewService reviewService;

    public ReviewController(final ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/")
    public List<ReviewDTO> findAll() {
        return this.reviewService.findAll();
    }

    @PostMapping("/")
    public ResponseEntity<ReviewDTO> create(@RequestBody ReviewDTO reviewDTO) {
        return new ResponseEntity<>(
            this.reviewService.save(reviewDTO),
            HeaderUtil.createSuccessAlert(entity),
            HttpStatus.CREATED
        );
    }
}
