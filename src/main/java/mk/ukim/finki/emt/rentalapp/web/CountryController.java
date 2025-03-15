package mk.ukim.finki.emt.rentalapp.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import mk.ukim.finki.emt.rentalapp.model.Country;
import mk.ukim.finki.emt.rentalapp.model.dto.CountryDto;
import mk.ukim.finki.emt.rentalapp.service.CountryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Countries", description = "API for managing countries")
@RestController
@RequestMapping("/api/countries")
public class CountryController {
    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @Operation(summary = "Get all countries", description = "Returns a list of all countries.")
    @GetMapping
    public List<Country> getAllCountries() {
        return countryService.findAll();
    }

    @Operation(summary = "Find country by ID", description = "Returns a specific country by its ID.")
    @GetMapping("/{id}")
    public ResponseEntity<Country> findById(@PathVariable Long id) {
        return countryService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Add a new country", description = "Creates a new country.")
    @PostMapping("/add")
    public ResponseEntity<Country> save(@RequestBody CountryDto country) {
        return countryService.save(country)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Edit a country", description = "Updates an existing country.")
    @PutMapping("/edit/{id}")
    public ResponseEntity<Country> update(@PathVariable Long id, @RequestBody CountryDto country) {
        return countryService.update(id, country)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Delete a country", description = "Removes a country from the system.")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (countryService.findById(id).isPresent()) {
            countryService.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
