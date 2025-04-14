package mk.ukim.finki.emt.rentalapp.model.domain;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String comment;

    private Integer rating;

    @ManyToOne
    private Accommodation accommodation;

    public Review() {
    }

    public Review(String comment, Integer rating, Accommodation accommodation) {
        this.comment = comment;
        this.rating = rating;
        this.accommodation = accommodation;
    }
}
