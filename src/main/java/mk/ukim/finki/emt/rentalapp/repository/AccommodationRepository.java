package mk.ukim.finki.emt.rentalapp.repository;

import mk.ukim.finki.emt.rentalapp.model.domain.Accommodation;
import org.springframework.stereotype.Repository;

@Repository
public interface AccommodationRepository extends JpaSpecificationRepository<Accommodation, Long> {

}
