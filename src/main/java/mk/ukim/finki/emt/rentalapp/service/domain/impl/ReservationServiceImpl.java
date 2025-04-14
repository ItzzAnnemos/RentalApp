package mk.ukim.finki.emt.rentalapp.service.domain.impl;

import jakarta.transaction.Transactional;
import mk.ukim.finki.emt.rentalapp.model.domain.Accommodation;
import mk.ukim.finki.emt.rentalapp.model.domain.Reservation;
import mk.ukim.finki.emt.rentalapp.model.domain.User;
import mk.ukim.finki.emt.rentalapp.model.enumerations.ReservationStatus;
import mk.ukim.finki.emt.rentalapp.model.exceptions.*;
import mk.ukim.finki.emt.rentalapp.repository.ReservationRepository;
import mk.ukim.finki.emt.rentalapp.service.domain.AccommodationService;
import mk.ukim.finki.emt.rentalapp.service.domain.ReservationService;
import mk.ukim.finki.emt.rentalapp.service.domain.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationServiceImpl implements ReservationService {
    private final ReservationRepository reservationRepository;
    private final UserService userService;
    private final AccommodationService accommodationService;

    public ReservationServiceImpl(ReservationRepository reservationRepository,
                                  UserService userService,
                                  AccommodationService accommodationService) {
        this.reservationRepository = reservationRepository;
        this.userService = userService;
        this.accommodationService = accommodationService;
    }

    @Override
    public List<Reservation> listAllReservationsForUser(String username) {
        return reservationRepository.findByUser(userService.findByUsername(username));
    }

    @Override
    public List<Accommodation> listAllAccommodationsInReservation(Long id) {
        if (reservationRepository.findById(id).isEmpty())
            throw new ReservationNotFound(id);

        return reservationRepository.findById(id).get().getAccommodations();
    }

    @Override
    public Optional<Reservation> getActiveReservation(String username) {
        User user = userService.findByUsername(username);

        return Optional.of(reservationRepository
                .findByUserAndReservationStatus(user, ReservationStatus.CREATED)
                .orElseGet(() -> reservationRepository.save(new Reservation(user))));
    }

    @Override
    public Optional<Reservation> addAccommodationToReservation(String username, Long accommodationId) {
        if (getActiveReservation(username).isPresent()) {
            Reservation reservation = getActiveReservation(username).get();

            Accommodation accommodation = accommodationService.findById(accommodationId)
                    .orElseThrow(() -> new AccommodationNotFoundException(accommodationId));
            if (!reservation.getAccommodations().stream()
                    .filter(a -> a.getId().equals(accommodationId))
                    .toList()
                    .isEmpty())
                throw new AccommodationAlreadyInReservationException(accommodationId, username);

            reservation.getAccommodations().add(accommodation);
            return Optional.of(reservationRepository.save(reservation));
        }
        return Optional.empty();
    }

    @Override
    public Optional<Reservation> removeAccommodationFromReservation(String username, Long accommodationId) {
        if (getActiveReservation(username).isPresent()) {
            Reservation reservation = getActiveReservation(username).get();

            Accommodation accommodation = accommodationService.findById(accommodationId)
                    .orElseThrow(() -> new AccommodationNotFoundException(accommodationId));
            if (reservation.getAccommodations().stream()
                    .filter(a -> a.getId().equals(accommodationId))
                    .toList()
                    .isEmpty())
                throw new AccommodationNotFoundInReservationException(accommodationId, username);

            reservation.getAccommodations().remove(accommodation);
            return Optional.of(reservationRepository.save(reservation));
        }
        return Optional.empty();
    }

    @Override
    @Transactional
    public Optional<Reservation> bookAllAccommodations(String username) {
        if (getActiveReservation(username).isPresent()) {
            Reservation reservation = getActiveReservation(username).get();

            for (Accommodation accommodation : reservation.getAccommodations()) {
                if (accommodation.isRented())
                    throw new AccommodationUnavailableException(accommodation.getId());

                accommodationService.rent(accommodation.getId());
            }

            reservation.setReservationStatus(ReservationStatus.CONFIRMED);
            return Optional.of(reservationRepository.save(reservation));
        }
        return Optional.empty();
    }

    @Override
    public Optional<Reservation> cancelReservation(String username) {
        if (getActiveReservation(username).isPresent()) {
            Reservation reservation = getActiveReservation(username).get();

            reservation.setReservationStatus(ReservationStatus.CANCELED);
            return Optional.of(reservationRepository.save(reservation));
        }
        return Optional.empty();
    }
}
