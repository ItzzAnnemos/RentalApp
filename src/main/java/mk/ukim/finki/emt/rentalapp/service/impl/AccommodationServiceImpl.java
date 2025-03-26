package mk.ukim.finki.emt.rentalapp.service.impl;

import mk.ukim.finki.emt.rentalapp.model.Accommodation;
import mk.ukim.finki.emt.rentalapp.model.dto.AccommodationDto;
import mk.ukim.finki.emt.rentalapp.repository.AccommodationRepository;
import mk.ukim.finki.emt.rentalapp.service.AccommodationService;
import mk.ukim.finki.emt.rentalapp.service.HostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@Service
public class AccommodationServiceImpl implements AccommodationService {
    private final AccommodationRepository accommodationRepository;
    private final HostService hostService;

    public AccommodationServiceImpl(AccommodationRepository accommodationRepository,
                                    HostService hostService) {
        this.accommodationRepository = accommodationRepository;
        this.hostService = hostService;
    }

    @Override
    public List<Accommodation> findAll() {
        return this.accommodationRepository.findAll();
    }

    @Override
    public Optional<Accommodation> findById(Long id) {
        return this.accommodationRepository.findById(id);
    }

    @Override
    public Optional<Accommodation> save(AccommodationDto accommodation) {
        if (accommodation.getHost() != null &&
                hostService.findById(accommodation.getHost()).isPresent()) {
            return Optional.of(accommodationRepository.save(new Accommodation(accommodation.getName(),
                    accommodation.getCategory(), hostService.findById(accommodation.getHost()).get(),
                    accommodation.getNumRooms())));
        }
        return Optional.empty();
    }

    @Override
    public Optional<Accommodation> update(Long id, AccommodationDto accommodation) {
        return this.accommodationRepository.findById(id).map(existingAccommodation -> {
            if (accommodation.getName() != null) {
                existingAccommodation.setName(accommodation.getName());
            }
            if (accommodation.getCategory() != null) {
                existingAccommodation.setCategory(accommodation.getCategory());
            }
            if (accommodation.getNumRooms() != null) {
                existingAccommodation.setNumRooms(accommodation.getNumRooms());
            }
            if (accommodation.getHost() != null
                    && hostService.findById(accommodation.getHost()).isPresent()) {
                existingAccommodation.setHost(hostService.findById(accommodation.getHost()).get());
            }
            return accommodationRepository.save(existingAccommodation);
        });
    }

    @Override
    public Optional<Accommodation> rent(Long id) {
        return this.accommodationRepository.findById(id).map(existingAccommodation -> {
            existingAccommodation.setRented(true);

            return accommodationRepository.save(existingAccommodation);
        });
    }

    @Override
    public void deleteById(Long id) {
        this.accommodationRepository.deleteById(id);
    }
}
