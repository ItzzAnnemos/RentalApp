package mk.ukim.finki.emt.rentalapp.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.PRECONDITION_FAILED)
public class AccommodationAlreadyInReservationException extends RuntimeException {

    public AccommodationAlreadyInReservationException(Long id, String username) {
        super(String.format("Accommodation with id: %d already exists in temporary reservation for user with username %s", id, username));
    }
}
