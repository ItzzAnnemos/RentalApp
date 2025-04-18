package mk.ukim.finki.emt.rentalapp.service.application;

import mk.ukim.finki.emt.rentalapp.dto.CreateAccommodationDto;
import mk.ukim.finki.emt.rentalapp.dto.DisplayAccommodationDto;
import mk.ukim.finki.emt.rentalapp.model.enumerations.Category;

import java.util.List;
import java.util.Optional;

public interface AccommodationApplicationService {

    List<DisplayAccommodationDto> findAll(String name, Category category, Long hostId, Integer numRooms, Boolean isRented);

    Optional<DisplayAccommodationDto> findById(Long id);

    Optional<DisplayAccommodationDto> save(CreateAccommodationDto accommodationDto);

    Optional<DisplayAccommodationDto> update(Long id, CreateAccommodationDto accommodationDto);

    Optional<DisplayAccommodationDto> rent(Long id);

    void deleteById(Long id);
}
