package mk.ukim.finki.emt.rentalapp.service.application;

import mk.ukim.finki.emt.rentalapp.dto.CreateUserDto;
import mk.ukim.finki.emt.rentalapp.dto.DisplayUserDto;
import mk.ukim.finki.emt.rentalapp.dto.LoginResponseDto;
import mk.ukim.finki.emt.rentalapp.dto.LoginUserDto;

import java.util.Optional;

public interface UserApplicationService {

    Optional<DisplayUserDto> register(CreateUserDto createUserDto);

    Optional<LoginResponseDto> login(LoginUserDto loginUserDto);

    Optional<DisplayUserDto> findByUsername(String username);

}
