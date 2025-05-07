package mk.ukim.finki.emt.rentalapp.service;

import mk.ukim.finki.emt.rentalapp.model.domain.Reservation;
import mk.ukim.finki.emt.rentalapp.service.domain.ReservationService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReservationServiceTest {

    @Autowired
    private ReservationService reservationService;

    @Test
    public void testGetActiveReservation() {
        Optional<Reservation> opt = reservationService.getActiveReservation("user");
        Assert.assertTrue(opt.isPresent());
    }
}
