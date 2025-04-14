package mk.ukim.finki.emt.rentalapp.dto;

import mk.ukim.finki.emt.rentalapp.model.domain.User;
import mk.ukim.finki.emt.rentalapp.model.enumerations.Role;

public record CreateUserDto(
        String username,
        String password,
        String repeatPassword,
        String name,
        String surname,
        Role role
) {

    /*
        todo: add repeat password logic
     */
    public User toUser() {
        return new User(username, password, name, surname, role);
    }
}

