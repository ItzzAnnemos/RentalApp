package mk.ukim.finki.emt.rentalapp.service.application.impl;

import mk.ukim.finki.emt.rentalapp.dto.CreateCountryDto;
import mk.ukim.finki.emt.rentalapp.dto.DisplayCountryDto;
import mk.ukim.finki.emt.rentalapp.service.application.CountryApplicationService;
import mk.ukim.finki.emt.rentalapp.service.domain.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CountryApplicationServiceImpl implements CountryApplicationService {
    private final CountryService countryService;

    public CountryApplicationServiceImpl(CountryService countryService) {
        this.countryService = countryService;
    }

    @Override
    public List<DisplayCountryDto> findAll() {
        return countryService.findAll().stream()
                .map(DisplayCountryDto::from)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<DisplayCountryDto> findById(Long id) {
        return countryService.findById(id).map(DisplayCountryDto::from);
    }

    @Override
    public Optional<DisplayCountryDto> save(CreateCountryDto countryDto) {
        return countryService.save(countryDto.toCountry()).map(DisplayCountryDto::from);
    }

    @Override
    public Optional<DisplayCountryDto> update(Long id, CreateCountryDto countryDto) {
        return countryService.update(id,
                        countryDto.toCountry())
                .map(DisplayCountryDto::from);
    }

    @Override
    public void deleteById(Long id) {
        countryService.deleteById(id);
    }
}
