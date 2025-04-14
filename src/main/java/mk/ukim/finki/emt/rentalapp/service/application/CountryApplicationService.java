package mk.ukim.finki.emt.rentalapp.service.application;

import mk.ukim.finki.emt.rentalapp.dto.CreateCountryDto;
import mk.ukim.finki.emt.rentalapp.dto.DisplayCountryDto;

import java.util.List;
import java.util.Optional;

public interface CountryApplicationService {

    List<DisplayCountryDto> findAll();

    Optional<DisplayCountryDto> findById(Long id);

    Optional<DisplayCountryDto> save(CreateCountryDto countryDto);

    Optional<DisplayCountryDto> update(Long id, CreateCountryDto countryDto);

    void deleteById(Long id);
}
