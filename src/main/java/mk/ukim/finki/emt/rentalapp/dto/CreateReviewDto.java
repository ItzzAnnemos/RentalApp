package mk.ukim.finki.emt.rentalapp.dto;

import mk.ukim.finki.emt.rentalapp.model.domain.Accommodation;
import mk.ukim.finki.emt.rentalapp.model.domain.Review;

import java.util.List;
import java.util.stream.Collectors;

public record CreateReviewDto (
        String comment,
        Integer rating,
        Long accommodation
) {

    public static CreateReviewDto from(Review review) {
        return new CreateReviewDto(
                review.getComment(),
                review.getRating(),
                review.getAccommodation().getId()
        );
    }

    public static List<CreateReviewDto> from(List<Review> reviews) {
        return reviews.stream()
                .map(CreateReviewDto::from)
                .collect(Collectors.toList());
    }

    public Review toReview(Accommodation accommodation) {
        return new Review(comment, rating, accommodation);
    }
}
