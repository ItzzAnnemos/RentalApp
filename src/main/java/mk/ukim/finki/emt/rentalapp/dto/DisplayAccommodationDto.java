package mk.ukim.finki.emt.rentalapp.dto;

import mk.ukim.finki.emt.rentalapp.model.domain.Accommodation;
import mk.ukim.finki.emt.rentalapp.model.domain.Host;
import mk.ukim.finki.emt.rentalapp.model.enumerations.Category;

import java.util.List;
import java.util.stream.Collectors;

public record DisplayAccommodationDto (
        Long id,
        String name,
        Category category,
        Long host,
        Integer numRooms,
        boolean isRented
) {

    public static DisplayAccommodationDto from(Accommodation accommodation) {
        return new DisplayAccommodationDto(
                accommodation.getId(),
                accommodation.getName(),
                accommodation.getCategory(),
                accommodation.getHost().getId(),
                accommodation.getNumRooms(),
                accommodation.isRented()
        );
    }

    public static List<DisplayAccommodationDto> from (List<Accommodation> accommodations) {
        return accommodations.stream()
                .map(DisplayAccommodationDto::from)
                .collect(Collectors.toList());
    }

    public Accommodation toAccommodation(Host host) {
        return new Accommodation(name, category, host, numRooms, isRented);
    }
}
