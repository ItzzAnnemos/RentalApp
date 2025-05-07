package mk.ukim.finki.emt.rentalapp.web.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import mk.ukim.finki.emt.rentalapp.dto.CreateAccommodationDto;
import mk.ukim.finki.emt.rentalapp.dto.DisplayAccommodationDto;
import mk.ukim.finki.emt.rentalapp.dto.DisplayReviewDto;
import mk.ukim.finki.emt.rentalapp.model.enumerations.Category;
import mk.ukim.finki.emt.rentalapp.model.views.AccommodationsPerHostView;
import mk.ukim.finki.emt.rentalapp.service.application.AccommodationApplicationService;
import mk.ukim.finki.emt.rentalapp.service.application.ReviewApplicationService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Accommodations", description = "API for managing accommodations")
@RestController
@RequestMapping("/api/accommodations")
public class AccommodationController {
    private final AccommodationApplicationService accommodationApplicationService;
    private final ReviewApplicationService reviewApplicationService;

    public AccommodationController(AccommodationApplicationService accommodationApplicationService,
                                   ReviewApplicationService reviewApplicationService) {
        this.accommodationApplicationService = accommodationApplicationService;
        this.reviewApplicationService = reviewApplicationService;
    }

    @Operation(summary = "Get all accommodations", description = "Returns a list of all accommodations")
    @GetMapping("/all")
    public List<DisplayAccommodationDto> findAll() {
        return this.accommodationApplicationService.findAll();
    }

    @Operation(summary = "Get paging of all accommodations", description = "Returns a page of all accommodations")
    @GetMapping()
    public Page<DisplayAccommodationDto> findAll(@RequestParam(required = false) String name,
                                                 @RequestParam(required = false) Category category,
                                                 @RequestParam(required = false) Long hostId,
                                                 @RequestParam(required = false) Integer numRooms,
                                                 @RequestParam(defaultValue = "false") Boolean isRented,
                                                 Pageable pageable
                                                 ) {
        return this.accommodationApplicationService.findAll(name, category, hostId, numRooms, isRented, pageable);
    }

    @Operation(summary = "Find accommodation by ID", description = "Returns a specific accommodation by its ID.")
    @GetMapping("/{id}")
    public ResponseEntity<DisplayAccommodationDto> findById(@PathVariable Long id) {
        return this.accommodationApplicationService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Get number of accommodations per host", description = "Return the number of accommodations per host")
    @GetMapping("/by-host")
    public List<AccommodationsPerHostView> getAccommodationsByHost() {
        return accommodationApplicationService.getAccommodationsPerHost();
    }

    @Operation(summary = "Add a new accommodation", description = "Creates a new accommodation.")
    @PostMapping("/add")
    public ResponseEntity<DisplayAccommodationDto> save(@RequestBody CreateAccommodationDto accommodation) {
        return this.accommodationApplicationService.save(accommodation)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Edit an accommodation", description = "Updates an existing accommodation.")
    @PutMapping("/edit/{id}")
    public ResponseEntity<DisplayAccommodationDto> update(@PathVariable Long id,
                                                          @RequestBody CreateAccommodationDto accommodation) {
        return this.accommodationApplicationService.update(id, accommodation)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Mark accommodation as rented", description = "Marks an accommodation as rented.")
    @PatchMapping("/rent/{id}")
    public ResponseEntity<DisplayAccommodationDto> rent(@PathVariable Long id) {
        return this.accommodationApplicationService.rent(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Average rating for accommodation", description = "Returns the average of the ratings of an accommodation.")
    @GetMapping("/{id}/average")
    @PreAuthorize("hasRole('HOST')")
    public Double getAverageRating(@PathVariable Long id) {
        return this.reviewApplicationService.getAverageScoreForAccommodation(id);
    }

    @Operation(summary = "Get all reviews for accommodation", description = "Returns a list of all the reviews for the specific accommodation")
    @GetMapping("/{id}/reviews")
    public List<DisplayReviewDto> getReviewsForAccommodation(@PathVariable Long id) {
        return this.reviewApplicationService.findAllByAccommodationId(id);
    }

    @Operation(summary = "Delete an accommodation", description = "Removes an accommodation from the system.")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (this.accommodationApplicationService.findById(id).isPresent()) {
            accommodationApplicationService.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
