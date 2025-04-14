package mk.ukim.finki.emt.rentalapp.model.exceptions;

public class PasswordsDoNotMatchException extends RuntimeException {
    public PasswordsDoNotMatchException() {
        super("Passwords do not match exception.");
    }
}
