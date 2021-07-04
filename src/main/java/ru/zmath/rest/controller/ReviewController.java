package ru.zmath.rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.zmath.rest.controller.util.HeaderUtil;
import ru.zmath.rest.service.ReviewService;
import ru.zmath.rest.service.dto.ReviewDTO;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(final ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/review")
    public ResponseEntity<List<ReviewDTO>> findAll() {
        return new ResponseEntity<>(
            this.reviewService.findAll(),
            HttpStatus.OK
        );
    }

    @PostMapping("/review")
    public ResponseEntity<ReviewDTO> create(@RequestBody ReviewDTO reviewDTO) {
        ReviewDTO result = this.reviewService.save(reviewDTO);
        return new ResponseEntity<>(
            result,
            HeaderUtil.createSuccessAlert("reviewCreate", String.valueOf(result.getId())),
            HttpStatus.CREATED
        );
    }
}
