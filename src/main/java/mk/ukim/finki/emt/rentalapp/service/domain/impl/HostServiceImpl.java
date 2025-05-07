package mk.ukim.finki.emt.rentalapp.service.domain.impl;

import mk.ukim.finki.emt.rentalapp.events.HostChangedEvent;
import mk.ukim.finki.emt.rentalapp.events.HostCreatedEvent;
import mk.ukim.finki.emt.rentalapp.events.HostDeletedEvent;
import mk.ukim.finki.emt.rentalapp.model.domain.Country;
import mk.ukim.finki.emt.rentalapp.model.domain.Host;
import mk.ukim.finki.emt.rentalapp.model.projections.HostProjection;
import mk.ukim.finki.emt.rentalapp.repository.HostRepository;
import mk.ukim.finki.emt.rentalapp.repository.HostsPerCountryViewRepository;
import mk.ukim.finki.emt.rentalapp.service.domain.CountryService;
import mk.ukim.finki.emt.rentalapp.service.domain.HostService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HostServiceImpl implements HostService {
    private final HostRepository hostRepository;
    private final CountryService countryService;
    private final HostsPerCountryViewRepository hostsPerCountryViewRepository;
    private final ApplicationEventPublisher applicationEventPublisher;

    public HostServiceImpl(HostRepository hostRepository,
                           CountryService countryService,
                           HostsPerCountryViewRepository hostsPerCountryViewRepository,
                           ApplicationEventPublisher applicationEventPublisher) {
        this.hostRepository = hostRepository;
        this.countryService = countryService;
        this.hostsPerCountryViewRepository = hostsPerCountryViewRepository;
        this.applicationEventPublisher = applicationEventPublisher;
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
    public Optional<Host> save(Host host) {
        Optional<Country> countryOpt = countryService.findById(host.getCountry().getId());

        if (countryOpt.isPresent()) {
            Host savedHost = this.hostRepository.save(new Host(host.getName(), host.getSurname(), countryOpt.get()));
            this.applicationEventPublisher.publishEvent(new HostCreatedEvent(savedHost));
            return Optional.of(savedHost);
        }
        return Optional.empty();
    }

    @Override
    public Optional<Host> update(Long id, Host host) {
        return this.hostRepository.findById(id).map(existingHost -> {
            if (host.getName() != null) {
                existingHost.setName(host.getName());
            }
            if (host.getSurname() != null) {
                existingHost.setSurname(host.getSurname());
            }
            if (host.getCountry() != null &&
                    countryService.findById(host.getCountry().getId()).isPresent()) {
                existingHost.setCountry(countryService.findById(host.getCountry().getId()).get());
            }
            Host updatedHost = this.hostRepository.save(existingHost);
            this.applicationEventPublisher.publishEvent(new HostChangedEvent(updatedHost));
            return updatedHost;
        });
    }

    @Override
    public void deleteById(Long id) {
        this.findById(id).ifPresent(host -> {
            hostRepository.deleteById(id);
            applicationEventPublisher.publishEvent(new HostDeletedEvent(host));
        });
    }

    @Override
    public void refreshMaterializedView() {
        hostsPerCountryViewRepository.refreshMaterializedView();
    }

    @Override
    public List<HostProjection> getNamesAndSurnames() {
        return hostRepository.takeNameAndSurnameByProjection();
    }
}
