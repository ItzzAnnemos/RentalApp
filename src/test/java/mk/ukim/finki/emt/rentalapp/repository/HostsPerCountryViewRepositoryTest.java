package mk.ukim.finki.emt.rentalapp.repository;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HostsPerCountryViewRepositoryTest {

    @Autowired
    private HostsPerCountryViewRepository hostsPerCountryViewRepository;

    @Test
    public void testFindAll() {
        Assert.assertEquals(1, hostsPerCountryViewRepository.findById(2L).get().getNumHosts().intValue());
    }
}
