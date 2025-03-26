package mk.ukim.finki.emt.rentalapp.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import mk.ukim.finki.emt.rentalapp.model.Review;
import mk.ukim.finki.emt.rentalapp.model.dto.ReviewDto;
import mk.ukim.finki.emt.rentalapp.service.ReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Reviews", description = "API for managing reviews")
@RestController
@RequestMapping("/api/reviews")
public class ReviewController {
    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @Operation(summary = "Get all reviews", description = "Returns a list of all reviews")
    @GetMapping
    public List<Review> findAll()    {
        return reviewService.findAll();
    }

    @Operation(summary = "Find review by ID", description = "Returns a specific review by its ID.")
    @GetMapping("/{id}")
    public ResponseEntity<Review> findById(@PathVariable Long id) {
        return reviewService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Add a new review", description = "Creates a new review.")
    @PostMapping("/add")
    public ResponseEntity<Review> save(@RequestBody ReviewDto review) {
        return reviewService.save(review)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Edit a review", description = "Updates an existing review.")
    @PutMapping("/edit/{id}")
    public ResponseEntity<Review> edit(@PathVariable Long id, @RequestBody ReviewDto review) {
        return reviewService.update(id, review)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Delete a review", description = "Removes a review from the system.")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (reviewService.findById(id).isPresent()) {
            reviewService.delete(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
