package mk.ukim.finki.emt.rentalapp.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class AccommodationNotFoundException extends RuntimeException {

    public AccommodationNotFoundException(Long id) {
        super(String.format("Accommodation with id %s was not found", id));
    }
}
