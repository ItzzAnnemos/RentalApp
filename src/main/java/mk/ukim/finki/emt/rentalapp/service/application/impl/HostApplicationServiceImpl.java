package mk.ukim.finki.emt.rentalapp.service.application.impl;

import mk.ukim.finki.emt.rentalapp.dto.CreateHostDto;
import mk.ukim.finki.emt.rentalapp.dto.DisplayHostDto;
import mk.ukim.finki.emt.rentalapp.model.domain.Country;
import mk.ukim.finki.emt.rentalapp.model.projections.HostProjection;
import mk.ukim.finki.emt.rentalapp.service.application.HostApplicationService;
import mk.ukim.finki.emt.rentalapp.service.domain.CountryService;
import mk.ukim.finki.emt.rentalapp.service.domain.HostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HostApplicationServiceImpl implements HostApplicationService {
    private final HostService hostService;
    private final CountryService countryService;

    public HostApplicationServiceImpl(HostService hostService,
                                      CountryService countryService) {
        this.hostService = hostService;
        this.countryService = countryService;
    }

    @Override
    public List<DisplayHostDto> findAll() {
        return hostService.findAll().stream()
                .map(DisplayHostDto::from)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<DisplayHostDto> findById(Long id) {
        return hostService.findById(id).map(DisplayHostDto::from);
    }

    @Override
    public Optional<DisplayHostDto> save(CreateHostDto hostDto) {
        Optional<Country> country = countryService.findById(hostDto.country());

        if (country.isPresent()) {
            return hostService.save(hostDto.toHost(country.get()))
                    .map(DisplayHostDto::from);
        }
        return Optional.empty();
    }

    @Override
    public Optional<DisplayHostDto> update(Long id, CreateHostDto hostDto) {
        Optional<Country> country = countryService.findById(hostDto.country());

        return hostService.update(id,
                        hostDto.toHost(country.orElse(null)))
                .map(DisplayHostDto::from);
    }

    @Override
    public void deleteById(Long id) {
        hostService.deleteById(id);
    }

    @Override
    public List<HostProjection> getNamesAndSurnames() {
        return hostService.getNamesAndSurnames();
    }
}
