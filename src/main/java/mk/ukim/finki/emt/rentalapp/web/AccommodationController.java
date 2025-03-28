package mk.ukim.finki.emt.rentalapp.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import mk.ukim.finki.emt.rentalapp.model.Accommodation;
import mk.ukim.finki.emt.rentalapp.model.Review;
import mk.ukim.finki.emt.rentalapp.model.dto.AccommodationDto;
import mk.ukim.finki.emt.rentalapp.service.AccommodationService;
import mk.ukim.finki.emt.rentalapp.service.ReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Tag(name = "Accommodations", description = "API for managing accommodations")
@RestController
@RequestMapping("/api/accommodations")
public class AccommodationController {
    private final AccommodationService accommodationService;
    private final ReviewService reviewService;

    public AccommodationController(AccommodationService accommodationService,
                                   ReviewService reviewService) {
        this.accommodationService = accommodationService;
        this.reviewService = reviewService;
    }

    @Operation(summary = "Get all accommodations", description = "Returns a list of all accommodations")
    @GetMapping
    public List<Accommodation> findAll() {
        return this.accommodationService.findAll();
    }

    @Operation(summary = "Find accommodation by ID", description = "Returns a specific accommodation by its ID.")
    @GetMapping("/{id}")
    public ResponseEntity<Accommodation> findById(@PathVariable Long id) {
        return this.accommodationService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Add a new accommodation", description = "Creates a new accommodation.")
    @PostMapping("/add")
    public ResponseEntity<Accommodation> save(@RequestBody AccommodationDto accommodation) {
        return this.accommodationService.save(accommodation)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Edit an accommodation", description = "Updates an existing accommodation.")
    @PutMapping("/edit/{id}")
    public ResponseEntity<Accommodation> update(@PathVariable Long id,
                                                @RequestBody AccommodationDto accommodation) {
        return this.accommodationService.update(id, accommodation)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Mark accommodation as rented", description = "Marks an accommodation as rented.")
    @PatchMapping("/rent/{id}")
    public ResponseEntity<Accommodation> rent(@PathVariable Long id) {
        return this.accommodationService.rent(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Average rating for accommodation", description = "Returns the average of the ratings of an accommodation.")
    @GetMapping("/{id}/average")
    public Double getAverageRating(@PathVariable Long id) {
        return this.reviewService.getAverageScoreForAccommodation(id);
    }

    @Operation(summary = "Get all reviews for accommodation", description = "Returns a list of all the reviews for the specific accommodation")
    @GetMapping("/{id}/reviews")
    public List<Review> getReviewsForAccommodation(@PathVariable Long id) {
        return this.reviewService.findAllByAccommodationId(id);
    }

    @Operation(summary = "Delete an accommodation", description = "Removes an accommodation from the system.")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (this.accommodationService.findById(id).isPresent()) {
            accommodationService.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
