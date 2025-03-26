package mk.ukim.finki.emt.rentalapp.model.dto;

import lombok.Data;

@Data
public class ReviewDto {
    private String comment;

    private Integer rating;

    private Long accommodation;

    public ReviewDto() {
    }

    public ReviewDto(String comment, Integer rating, Long accommodation) {
        this.comment = comment;
        this.rating = rating;
        this.accommodation = accommodation;
    }
}
