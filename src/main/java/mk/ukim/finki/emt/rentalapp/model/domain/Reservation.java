package mk.ukim.finki.emt.rentalapp.model.domain;

import jakarta.persistence.*;
import lombok.Data;
import mk.ukim.finki.emt.rentalapp.model.enumerations.ReservationStatus;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Accommodation> accommodations;

    @Enumerated(EnumType.STRING)
    private ReservationStatus reservationStatus;

    public Reservation() {
    }

    public Reservation(User user) {
        this.user = user;
        this.accommodations = new ArrayList<>();
        this.reservationStatus = ReservationStatus.CREATED;
    }
}
