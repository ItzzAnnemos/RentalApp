package mk.ukim.finki.emt.rentalapp.model.exceptions;

public class AccommodationNotFoundInReservationException extends RuntimeException {

    public AccommodationNotFoundInReservationException(Long id, String username) {
        super(String.format("Accommodation with id: %d not found in reservation for user with username %s", id, username));
    }
}
