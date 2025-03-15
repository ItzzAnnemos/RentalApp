package mk.ukim.finki.emt.rentalapp.service.impl;

import mk.ukim.finki.emt.rentalapp.model.Host;
import mk.ukim.finki.emt.rentalapp.model.dto.HostDto;
import mk.ukim.finki.emt.rentalapp.repository.HostRepository;
import mk.ukim.finki.emt.rentalapp.service.CountryService;
import mk.ukim.finki.emt.rentalapp.service.HostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HostServiceImpl implements HostService {
    private final HostRepository hostRepository;
    private final CountryService countryService;

    public HostServiceImpl(HostRepository hostRepository, CountryService countryService) {
        this.hostRepository = hostRepository;
        this.countryService = countryService;
    }

    @Override
    public List<Host> findAll() {
        return this.hostRepository.findAll();
    }

    @Override
    public Optional<Host> findById(Long id) {
        return this.hostRepository.findById(id);
    }

    @Override
    public Optional<Host> save(HostDto host) {
        if (host.getCountry() != null && countryService.findById(host.getCountry()).isPresent()) {
            return Optional.of(this.hostRepository.save(new Host(host.getName(), host.getSurname(),
                    countryService.findById(host.getCountry()).get())));
        }
        return Optional.empty();
    }

    @Override
    public Optional<Host> update(Long id, HostDto host) {
        return this.hostRepository.findById(id).map(existingHost -> {
            if (host.getName() != null) {
                existingHost.setName(host.getName());
            }
            if (host.getSurname() != null) {
                existingHost.setSurname(host.getSurname());
            }
            if (host.getCountry() != null &&
                    countryService.findById(host.getCountry()).isPresent()) {
                existingHost.setCountry(countryService.findById(host.getCountry()).get());
            }
            return hostRepository.save(existingHost);
        });
    }

    @Override
    public void deleteById(Long id) {
        this.hostRepository.deleteById(id);
    }
}
