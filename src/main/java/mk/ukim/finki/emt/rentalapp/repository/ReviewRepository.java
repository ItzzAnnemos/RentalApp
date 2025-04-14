package mk.ukim.finki.emt.rentalapp.repository;

import mk.ukim.finki.emt.rentalapp.model.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findAllByAccommodationId(Long accommodationId);
}
