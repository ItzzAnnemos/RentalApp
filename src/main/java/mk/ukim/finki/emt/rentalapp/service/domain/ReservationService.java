package mk.ukim.finki.emt.rentalapp.service.domain;

import mk.ukim.finki.emt.rentalapp.model.domain.Accommodation;
import mk.ukim.finki.emt.rentalapp.model.domain.Reservation;

import java.util.List;
import java.util.Optional;

public interface ReservationService {

    List<Reservation> listAllReservationsForUser(String username);

    List<Accommodation> listAllAccommodationsInReservation(Long id);

    Optional<Reservation> getActiveReservation(String username);

    Optional<Reservation> addAccommodationToReservation(String username, Long accommodationId);

    Optional<Reservation> removeAccommodationFromReservation(String username, Long accommodationId);

    Optional<Reservation> bookAllAccommodations(String username);

    Optional<Reservation> cancelReservation(String username);
}
