package mk.ukim.finki.emt.rentalapp.service.application.impl;

import mk.ukim.finki.emt.rentalapp.dto.CreateAccommodationDto;
import mk.ukim.finki.emt.rentalapp.dto.DisplayAccommodationDto;
import mk.ukim.finki.emt.rentalapp.model.domain.Host;
import mk.ukim.finki.emt.rentalapp.service.application.AccommodationApplicationService;
import mk.ukim.finki.emt.rentalapp.service.domain.AccommodationService;
import mk.ukim.finki.emt.rentalapp.service.domain.HostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AccommodationApplicationServiceImpl implements AccommodationApplicationService {
    private final AccommodationService accommodationService;
    private final HostService hostService;

    public AccommodationApplicationServiceImpl(AccommodationService accommodationService,
                                               HostService hostService) {
        this.accommodationService = accommodationService;
        this.hostService = hostService;
    }

    @Override
    public List<DisplayAccommodationDto> findAll() {
        return accommodationService.findAll().stream()
                .map(DisplayAccommodationDto::from)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<DisplayAccommodationDto> findById(Long id) {
        return accommodationService.findById(id).map(DisplayAccommodationDto::from);
    }

    @Override
    public Optional<DisplayAccommodationDto> save(CreateAccommodationDto accommodationDto) {
        Optional<Host> host = hostService.findById(accommodationDto.host());

        if (host.isPresent()) {
            return accommodationService.save(accommodationDto.toAccommodation(host.get()))
                    .map(DisplayAccommodationDto::from);
        }
        return Optional.empty();
    }

    @Override
    public Optional<DisplayAccommodationDto> update(Long id, CreateAccommodationDto accommodationDto) {
        Optional<Host> host = hostService.findById(accommodationDto.host());

        return accommodationService.update(id,
                        accommodationDto.toAccommodation(host.orElse(null)))
                .map(DisplayAccommodationDto::from);
    }

    @Override
    public Optional<DisplayAccommodationDto> rent(Long id) {
        return accommodationService.rent(id).map(DisplayAccommodationDto::from);
    }

    @Override
    public void deleteById(Long id) {
        accommodationService.deleteById(id);
    }
}
