package mk.ukim.finki.emt.rentalapp.service;

import mk.ukim.finki.emt.rentalapp.model.Accommodation;
import mk.ukim.finki.emt.rentalapp.model.dto.AccommodationDto;

import java.util.List;
import java.util.Optional;

public interface AccommodationService {
    List<Accommodation> findAll();

    Optional<Accommodation> findById(Long id);

    Optional<Accommodation> save(AccommodationDto accommodation);

    Optional<Accommodation> update(Long id, AccommodationDto accommodation);

    Optional<Accommodation> rent(Long id);

    void deleteById(Long id);
}
