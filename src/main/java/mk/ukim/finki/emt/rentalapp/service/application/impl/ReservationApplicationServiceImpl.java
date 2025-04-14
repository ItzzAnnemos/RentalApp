package mk.ukim.finki.emt.rentalapp.service.application.impl;

import mk.ukim.finki.emt.rentalapp.dto.DisplayAccommodationDto;
import mk.ukim.finki.emt.rentalapp.dto.ReservationDto;
import mk.ukim.finki.emt.rentalapp.model.exceptions.AccommodationUnavailableException;
import mk.ukim.finki.emt.rentalapp.service.application.ReservationApplicationService;
import mk.ukim.finki.emt.rentalapp.service.domain.AccommodationService;
import mk.ukim.finki.emt.rentalapp.service.domain.ReservationService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReservationApplicationServiceImpl implements ReservationApplicationService {

    private final ReservationService reservationService;
    private final AccommodationService accommodationService;

    public ReservationApplicationServiceImpl(ReservationService reservationService,
                                             AccommodationService accommodationService) {
        this.reservationService = reservationService;
        this.accommodationService = accommodationService;
    }


    @Override
    public List<ReservationDto> listAllReservationsForUser(String username) {
        return reservationService.listAllReservationsForUser(username).stream()
                .map(ReservationDto::from)
                .collect(Collectors.toList());
    }

    @Override
    public List<DisplayAccommodationDto> listAllAccommodationsInReservation(Long id) {
        return DisplayAccommodationDto.from(reservationService.listAllAccommodationsInReservation(id));
    }

    @Override
    public Optional<ReservationDto> getActiveReservation(String username) {
        return reservationService.getActiveReservation(username).map(ReservationDto::from);
    }

    @Override
    public Optional<ReservationDto> addAccommodationToReservation(String username, Long accommodationId) {
        var optionalAcc = accommodationService.findById(accommodationId);

        if (optionalAcc.isEmpty() || optionalAcc.get().isRented())
            throw new AccommodationUnavailableException(accommodationId);

        return reservationService.addAccommodationToReservation(username, accommodationId)
                .map(ReservationDto::from);
    }

    @Override
    public Optional<ReservationDto> removeAccommodationFromReservation(String username, Long accommodationId) {
        if (accommodationService.findById(accommodationId).isPresent()) {
            return reservationService.removeAccommodationFromReservation(username, accommodationId)
                    .map(ReservationDto::from);
        }

        return Optional.empty();
    }

    @Override
    public Optional<ReservationDto> bookAllAccommodations(String username) {
        return reservationService.bookAllAccommodations(username)
                .map(ReservationDto::from);
    }

    @Override
    public Optional<ReservationDto> cancelReservation(String username) {
        return reservationService.cancelReservation(username)
                .map(ReservationDto::from);
    }
}
