package mk.ukim.finki.emt.rentalapp.service.domain;

import mk.ukim.finki.emt.rentalapp.model.domain.Review;

import java.util.List;
import java.util.Optional;

public interface ReviewService {
    List<Review> findAll();

    List<Review> findAllByAccommodationId(Long accommodationId);

    Double getAverageScoreForAccommodation(Long accommodationId);

    Optional<Review> findById(Long id);

    Optional<Review> save(Review review);

    Optional<Review> update(Long id, Review review);

    void deleteById(Long id);

}
