package mk.ukim.finki.emt.rentalapp.repository;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccommodationsPerHostViewRepositoryTest {

    @Autowired
    private AccommodationsPerHostViewRepository accommodationsPerHostViewRepository;

    @Test
    public void testFindAll() {
        Assert.assertEquals(2, accommodationsPerHostViewRepository.findByHostId(1L).getNumAccommodations().intValue());
    }
}