package mk.ukim.finki.emt.rentalapp.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import mk.ukim.finki.emt.rentalapp.model.Host;
import mk.ukim.finki.emt.rentalapp.model.dto.HostDto;
import mk.ukim.finki.emt.rentalapp.service.HostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Tag(name = "Hosts", description = "API for managing hosts")
@RestController
@RequestMapping("/api/hosts")
public class HostController {
    private final HostService hostService;

    public HostController(HostService hostService) {
        this.hostService = hostService;
    }

    @Operation(summary = "Get all hosts", description = "Returns a list of all hosts.")
    @GetMapping
    public List<Host> findAll() {
        return hostService.findAll();
    }

    @Operation(summary = "Find host by ID", description = "Returns a specific host by its ID.")
    @GetMapping("/{id}")
    public ResponseEntity<Host> findById(@PathVariable Long id) {
        return this.hostService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Add a new host", description = "Creates a new host.")
    @PostMapping("/add")
    public ResponseEntity<Host> save(@RequestBody HostDto host) {
        return this.hostService.save(host)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Edit a host", description = "Updates an existing host.")
    @PutMapping("/edit/{id}")
    public ResponseEntity<Host> update(@PathVariable Long id, @RequestBody HostDto host) {
        return this.hostService.update(id, host)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Delete a host", description = "Removes a host from the system.")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (hostService.findById(id).isPresent()) {
            hostService.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
