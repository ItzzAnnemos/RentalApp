package mk.ukim.finki.emt.rentalapp.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ReservationNotFound extends RuntimeException {

    public ReservationNotFound(Long id) {
        super(String.format("Reservation with id: %d was not found", id));
    }
}
