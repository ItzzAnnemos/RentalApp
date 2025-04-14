package mk.ukim.finki.emt.rentalapp.service.domain;

import mk.ukim.finki.emt.rentalapp.model.domain.Accommodation;

import java.util.List;
import java.util.Optional;

public interface AccommodationService {
    List<Accommodation> findAll();

    Optional<Accommodation> findById(Long id);

    Optional<Accommodation> save(Accommodation accommodation);

    Optional<Accommodation> update(Long id, Accommodation accommodation);

    Optional<Accommodation> rent(Long id);

    void deleteById(Long id);
}
