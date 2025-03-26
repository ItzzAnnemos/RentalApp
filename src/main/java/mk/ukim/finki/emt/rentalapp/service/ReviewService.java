package mk.ukim.finki.emt.rentalapp.service;

import mk.ukim.finki.emt.rentalapp.model.Review;
import mk.ukim.finki.emt.rentalapp.model.dto.ReviewDto;

import java.util.List;
import java.util.Optional;

public interface ReviewService {
    List<Review> findAll();

    List<Review> findAllByAccommodationId(Long accommodationId);

    Double getAverageScoreForAccommodation(Long accommodationId);

    Optional<Review> findById(Long id);

    Optional<Review> save(ReviewDto review);

    Optional<Review> update(Long id, ReviewDto review);

    void delete(Long id);

}
