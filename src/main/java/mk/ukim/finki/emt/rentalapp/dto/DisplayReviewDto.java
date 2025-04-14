package mk.ukim.finki.emt.rentalapp.dto;

import mk.ukim.finki.emt.rentalapp.model.domain.Accommodation;
import mk.ukim.finki.emt.rentalapp.model.domain.Review;

import java.util.List;
import java.util.stream.Collectors;

public record DisplayReviewDto(
        Long id,
        String comment,
        Integer rating,
        Long accommodation
) {

    public static DisplayReviewDto from(Review review) {
        return new DisplayReviewDto(
                review.getId(),
                review.getComment(),
                review.getRating(),
                review.getAccommodation().getId()
        );
    }

    public static List<DisplayReviewDto> from(List<Review> reviews) {
        return reviews.stream()
                .map(DisplayReviewDto::from)
                .collect(Collectors.toList());
    }

    public Review toReview(Accommodation accommodation) {
        return new Review(comment, rating, accommodation);
    }
}
