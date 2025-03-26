package mk.ukim.finki.emt.rentalapp.service.impl;

import mk.ukim.finki.emt.rentalapp.model.Review;
import mk.ukim.finki.emt.rentalapp.model.dto.ReviewDto;
import mk.ukim.finki.emt.rentalapp.repository.ReviewRepository;
import mk.ukim.finki.emt.rentalapp.service.AccommodationService;
import mk.ukim.finki.emt.rentalapp.service.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final AccommodationService accommodationService;

    public ReviewServiceImpl(ReviewRepository reviewRepository,
                             AccommodationService accommodationService) {
        this.reviewRepository = reviewRepository;
        this.accommodationService = accommodationService;
    }

    @Override
    public List<Review> findAll() {
        return reviewRepository.findAll();
    }

    @Override
    public List<Review> findAllByAccommodationId(Long accommodationId) {
        return reviewRepository.findAllByAccommodationId(accommodationId);
    }

    @Override
    public Double getAverageScoreForAccommodation(Long accommodationId) {
        return reviewRepository.findAllByAccommodationId(accommodationId)
                .stream()
                .flatMapToInt(review -> IntStream.of(review.getRating()))
                .average()
                .orElse(0.0);

    }

    @Override
    public Optional<Review> findById(Long id) {
        return reviewRepository.findById(id);
    }

    @Override
    public Optional<Review> save(ReviewDto review) {
        if (review.getAccommodation() != null
                && accommodationService.findById(review.getAccommodation()).isPresent()) {
            return Optional.of(reviewRepository.save(new Review(review.getComment(),
                    review.getRating(), accommodationService.findById(review.getAccommodation()).get())));
        }
        return Optional.empty();
    }

    @Override
    public Optional<Review> update(Long id, ReviewDto review) {
        return reviewRepository.findById(id).map(existingReview -> {
            if (review.getComment() != null) {
                existingReview.setComment(review.getComment());
            }
            if (review.getRating() != null) {
                existingReview.setRating(review.getRating());
            }
            if (review.getAccommodation() != null
                    && accommodationService.findById(review.getAccommodation()).isPresent()) {
                existingReview.setAccommodation(accommodationService.findById(review.getAccommodation()).get());
            }
            return reviewRepository.save(existingReview);
        });
    }

    @Override
    public void delete(Long id) {
        this.reviewRepository.deleteById(id);
    }
}
