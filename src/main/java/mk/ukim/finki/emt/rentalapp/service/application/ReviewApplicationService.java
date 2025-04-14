package mk.ukim.finki.emt.rentalapp.service.application;

import mk.ukim.finki.emt.rentalapp.dto.CreateReviewDto;
import mk.ukim.finki.emt.rentalapp.dto.DisplayReviewDto;

import java.util.List;
import java.util.Optional;

public interface ReviewApplicationService {

    List<DisplayReviewDto> findAll();

    List<DisplayReviewDto> findAllByAccommodationId(Long accommodationId);

    Double getAverageScoreForAccommodation(Long accommodationId);

    Optional<DisplayReviewDto> findById(Long id);

    Optional<DisplayReviewDto> save(CreateReviewDto reviewDto);

    Optional<DisplayReviewDto> update(Long id, CreateReviewDto reviewDto);

    void deleteById(Long id);
}
