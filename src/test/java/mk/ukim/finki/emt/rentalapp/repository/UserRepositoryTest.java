package mk.ukim.finki.emt.rentalapp.repository;

import mk.ukim.finki.emt.rentalapp.model.domain.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void testFindAll() {
        List<User> userList = this.userRepository.findAll();
        Assert.assertEquals(4L, userList.size());
    }

    @Test
    public void testFetchAll() {
        List<User> userList = this.userRepository.fetchAll();
        Assert.assertEquals(4L, userList.size());
        for (User user : userList) {
            Assert.assertNotNull(user.getReservations()); // Will initialize only if it was fetched
        }
    }

    @Test
    public void testLoadAll() {
        List<User> userList = this.userRepository.loadAll();
        Assert.assertEquals(4L, userList.size());
        for (User user : userList) {
            try {
                System.out.println(user.getReservations().size()); // If LAZY and session closed, throws LazyInitializationException
            } catch (Exception e) {
                Assert.assertTrue(e instanceof org.hibernate.LazyInitializationException);
            }
        }
    }
}
