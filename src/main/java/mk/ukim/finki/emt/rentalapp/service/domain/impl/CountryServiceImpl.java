package mk.ukim.finki.emt.rentalapp.service.domain.impl;

import mk.ukim.finki.emt.rentalapp.model.domain.Country;
import mk.ukim.finki.emt.rentalapp.repository.CountryRepository;
import mk.ukim.finki.emt.rentalapp.service.domain.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryServiceImpl implements CountryService {
    private final CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Country> findAll() {
        return this.countryRepository.findAll();
    }

    @Override
    public Optional<Country> findById(Long id) {
        return this.countryRepository.findById(id);
    }

    @Override
    public Optional<Country> save(Country country) {
        return Optional.of(this.countryRepository
                .save(new Country(country.getName(), country.getContinent())));
    }

    @Override
    public Optional<Country> update(Long id, Country country) {
        return this.countryRepository.findById(id).map(existingCountry -> {
            if (country.getName() != null) {
                existingCountry.setName(country.getName());
            }
            if (country.getContinent() != null) {
                existingCountry.setContinent(country.getContinent());
            }
            return countryRepository.save(existingCountry);
        });
    }

    @Override
    public void deleteById(Long id) {
        this.countryRepository.deleteById(id);
    }
}
