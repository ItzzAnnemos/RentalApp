package mk.ukim.finki.emt.rentalapp.web.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import mk.ukim.finki.emt.rentalapp.dto.CreateReviewDto;
import mk.ukim.finki.emt.rentalapp.dto.DisplayReviewDto;
import mk.ukim.finki.emt.rentalapp.service.application.ReviewApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Reviews", description = "API for managing reviews")
@RestController
@RequestMapping("/api/reviews")
public class ReviewController {
    private final ReviewApplicationService reviewApplicationService;

    public ReviewController(ReviewApplicationService reviewApplicationService) {
        this.reviewApplicationService = reviewApplicationService;
    }

    @Operation(summary = "Get all reviews", description = "Returns a list of all reviews")
    @GetMapping
    public List<DisplayReviewDto> findAll() {
        return reviewApplicationService.findAll();
    }

    @Operation(summary = "Find review by ID", description = "Returns a specific review by its ID.")
    @GetMapping("/{id}")
    public ResponseEntity<DisplayReviewDto> findById(@PathVariable Long id) {
        return reviewApplicationService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Add a new review", description = "Creates a new review.")
    @PostMapping("/add")
    public ResponseEntity<DisplayReviewDto> save(@RequestBody CreateReviewDto review) {
        return reviewApplicationService.save(review)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Edit a review", description = "Updates an existing review.")
    @PutMapping("/edit/{id}")
    public ResponseEntity<DisplayReviewDto> edit(@PathVariable Long id, @RequestBody CreateReviewDto review) {
        return reviewApplicationService.update(id, review)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Delete a review", description = "Removes a review from the system.")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (reviewApplicationService.findById(id).isPresent()) {
            reviewApplicationService.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
