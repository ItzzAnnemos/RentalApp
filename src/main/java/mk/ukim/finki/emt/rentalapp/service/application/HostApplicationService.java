package mk.ukim.finki.emt.rentalapp.service.application;

import mk.ukim.finki.emt.rentalapp.dto.CreateHostDto;
import mk.ukim.finki.emt.rentalapp.dto.DisplayHostDto;
import mk.ukim.finki.emt.rentalapp.model.projections.HostProjection;

import java.util.List;
import java.util.Optional;

public interface HostApplicationService {

    List<DisplayHostDto> findAll();

    Optional<DisplayHostDto> findById(Long id);

    Optional<DisplayHostDto> save(CreateHostDto hostDto);

    Optional<DisplayHostDto> update(Long id, CreateHostDto hostDto);

    void deleteById(Long id);

    List<HostProjection> getNamesAndSurnames();
}
