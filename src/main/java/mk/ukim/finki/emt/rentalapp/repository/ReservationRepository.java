package mk.ukim.finki.emt.rentalapp.repository;

import mk.ukim.finki.emt.rentalapp.model.domain.Reservation;
import mk.ukim.finki.emt.rentalapp.model.domain.User;
import mk.ukim.finki.emt.rentalapp.model.enumerations.ReservationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    List<Reservation> findByUser(User user);

    Optional<Reservation> findByUserAndReservationStatus(User user, ReservationStatus reservationStatus);
}
