package ru.zmath.back.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.zmath.back.service.dto.ReviewDTO;
import ru.zmath.back.model.Review;
import ru.zmath.back.repository.ReviewRepository;
import ru.zmath.back.service.mapper.ReviewMapper;

import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ReviewMapper reviewMapper;
    private final UserService userService;

    public ReviewService(final ReviewRepository reviewRepository, ReviewMapper reviewMapper, UserService userService) {
        this.reviewRepository = reviewRepository;
        this.reviewMapper = reviewMapper;
        this.userService = userService;
    }

    @Transactional(readOnly = true)
    public List<ReviewDTO> findAll() {
        return reviewRepository.findAll().stream()
            .map(reviewMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    @Transactional
    public ReviewDTO save(ReviewDTO reviewDTO) {
        Review review = this.reviewMapper.toEntity(reviewDTO);
        review.setCreated(GregorianCalendar.getInstance());
        review.setUser(userService.getCurrentUser().orElseThrow(
            () -> new RuntimeException("User not found in context")
        ));
        return this.reviewMapper.toDto(this.reviewRepository.save(review));
    }

    @Transactional(readOnly = true)
    public Optional<ReviewDTO> findById(int id) {
        return this.reviewRepository.findById(id)
            .map(this.reviewMapper::toDto);
    }

    @Transactional
    public boolean delete(int id) {
        return this.reviewRepository.deleteReviewById(id) != 0;
    }
}
