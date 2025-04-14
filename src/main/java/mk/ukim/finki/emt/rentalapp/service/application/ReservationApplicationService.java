package mk.ukim.finki.emt.rentalapp.service.application;

import mk.ukim.finki.emt.rentalapp.dto.DisplayAccommodationDto;
import mk.ukim.finki.emt.rentalapp.dto.ReservationDto;

import java.util.List;
import java.util.Optional;

public interface ReservationApplicationService {

    List<ReservationDto> listAllReservationsForUser(String username);

    List<DisplayAccommodationDto> listAllAccommodationsInReservation(Long id);

    Optional<ReservationDto> getActiveReservation(String username);

    Optional<ReservationDto> addAccommodationToReservation(String username, Long accommodationId);

    Optional<ReservationDto> removeAccommodationFromReservation(String username, Long accommodationId);

    Optional<ReservationDto> bookAllAccommodations(String username);

    Optional<ReservationDto> cancelReservation(String username);
}
