package mk.ukim.finki.emt.rentalapp.repository;

import mk.ukim.finki.emt.rentalapp.model.domain.Host;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HostRepository extends JpaRepository<Host, Long> {

}
