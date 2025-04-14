package mk.ukim.finki.emt.rentalapp.dto;

import mk.ukim.finki.emt.rentalapp.model.domain.Reservation;
import mk.ukim.finki.emt.rentalapp.model.enumerations.ReservationStatus;

import java.util.List;

public record ReservationDto(
        Long id,
        DisplayUserDto user,
        List<DisplayAccommodationDto> accommodations,
        ReservationStatus reservationStatus
) {

    public static ReservationDto from(Reservation reservation) {
        return new ReservationDto(
                reservation.getId(),
                DisplayUserDto.from(reservation.getUser()),
                DisplayAccommodationDto.from(reservation.getAccommodations()),
                reservation.getReservationStatus()
        );
    }
}
