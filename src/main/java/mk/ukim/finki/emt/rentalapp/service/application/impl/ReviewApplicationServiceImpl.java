package mk.ukim.finki.emt.rentalapp.service.application.impl;

import mk.ukim.finki.emt.rentalapp.dto.CreateReviewDto;
import mk.ukim.finki.emt.rentalapp.dto.DisplayReviewDto;
import mk.ukim.finki.emt.rentalapp.model.domain.Accommodation;
import mk.ukim.finki.emt.rentalapp.service.application.ReviewApplicationService;
import mk.ukim.finki.emt.rentalapp.service.domain.AccommodationService;
import mk.ukim.finki.emt.rentalapp.service.domain.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReviewApplicationServiceImpl implements ReviewApplicationService {
    private final ReviewService reviewService;
    private final AccommodationService accommodationService;

    public ReviewApplicationServiceImpl(ReviewService reviewService, AccommodationService accommodationService) {
        this.reviewService = reviewService;
        this.accommodationService = accommodationService;
    }

    @Override
    public List<DisplayReviewDto> findAll() {
        return reviewService.findAll().stream()
                .map(DisplayReviewDto::from)
                .collect(Collectors.toList());
    }

    @Override
    public List<DisplayReviewDto> findAllByAccommodationId(Long accommodationId) {
        return reviewService.findAllByAccommodationId(accommodationId).stream()
                .map(DisplayReviewDto::from)
                .collect(Collectors.toList());
    }

    @Override
    public Double getAverageScoreForAccommodation(Long accommodationId) {
        return reviewService.getAverageScoreForAccommodation(accommodationId);
    }

    @Override
    public Optional<DisplayReviewDto> findById(Long id) {
        return reviewService.findById(id).map(DisplayReviewDto::from);
    }

    @Override
    public Optional<DisplayReviewDto> save(CreateReviewDto reviewDto) {
        Optional<Accommodation> accommodation = accommodationService.findById(reviewDto.accommodation());

        if (accommodation.isPresent()) {
            return reviewService.save(reviewDto.toReview(accommodation.get()))
                    .map(DisplayReviewDto::from);
        }
        return Optional.empty();
    }

    @Override
    public Optional<DisplayReviewDto> update(Long id, CreateReviewDto reviewDto) {
        Optional<Accommodation> accommodation = accommodationService.findById(reviewDto.accommodation());

        return reviewService.update(id,
                        reviewDto.toReview(accommodation.orElse(null)))
                .map(DisplayReviewDto::from);
    }

    @Override
    public void deleteById(Long id) {
        reviewService.deleteById(id);
    }
}
