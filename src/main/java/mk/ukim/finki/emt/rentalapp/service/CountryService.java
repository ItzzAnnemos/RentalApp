package mk.ukim.finki.emt.rentalapp.service;

import mk.ukim.finki.emt.rentalapp.model.Country;
import mk.ukim.finki.emt.rentalapp.model.dto.CountryDto;

import java.util.List;
import java.util.Optional;

public interface CountryService {
    List<Country> findAll();

    Optional<Country> findById(Long id);

    Optional<Country> save(CountryDto country);

    Optional<Country> update(Long id, CountryDto country);

    void deleteById(Long id);
}
