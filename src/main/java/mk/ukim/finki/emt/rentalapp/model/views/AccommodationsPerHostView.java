package mk.ukim.finki.emt.rentalapp.model.views;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

@Data
@Entity
@Subselect("SELECT * FROM public.accommodations_per_hosts")
@Immutable
public class AccommodationsPerHostView {

    @Id
    @Column(name = "host_id")
    private Long hostId;

    @Column(name = "host_name")
    private String hostName;

    @Column(name = "host_surname")
    private String hostSurname;

    @Column(name = "num_accommodations")
    private Integer numAccommodations;
}