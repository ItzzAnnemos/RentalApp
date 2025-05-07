package mk.ukim.finki.emt.rentalapp.config.init;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.emt.rentalapp.model.domain.*;
import mk.ukim.finki.emt.rentalapp.model.enumerations.Category;
import mk.ukim.finki.emt.rentalapp.model.enumerations.Role;
import mk.ukim.finki.emt.rentalapp.repository.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataInitializer {
    public static List<Country> countries;
    public static List<Host> hosts;
    public static List<Review> reviews;
    public static List<Accommodation> accommodations;
    public static List<User> users;

    private final AccommodationRepository accommodationRepository;
    private final CountryRepository countryRepository;
    private final HostRepository hostRepository;
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(AccommodationRepository accommodationRepository,
                           CountryRepository countryRepository,
                           HostRepository hostRepository,
                           ReviewRepository reviewRepository,
                           UserRepository userRepository,
                           PasswordEncoder passwordEncoder) {
        this.accommodationRepository = accommodationRepository;
        this.countryRepository = countryRepository;
        this.hostRepository = hostRepository;
        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void init() {
        countries = new ArrayList<>();
        if (this.countryRepository.count() == 0) {
            countries.add(new Country("Macedonia", "Europe"));
            countries.add(new Country("Germany", "Europe"));
            countries.add(new Country("United States", "North America"));
            this.countryRepository.saveAll(countries);
        } else {
            countries = countryRepository.findAll();
        }

        users = new ArrayList<>();
        if (this.userRepository.count() == 0) {
            User hostUser1 = new User("nikola.serafimov", passwordEncoder.encode("ns"), "Nikola", "Serafimov", Role.HOST);
            User hostUser2 = new User("felix.schneider", passwordEncoder.encode("fs"), "Felix", "Schneider", Role.HOST);
            User hostUser3 = new User("john.matthews", passwordEncoder.encode("jm"), "John", "Matthews", Role.HOST);
            User regularUser = new User("user", passwordEncoder.encode("user"), "User", "", Role.USER);
            users = List.of(hostUser1, hostUser2, hostUser3, regularUser);
            this.userRepository.saveAll(users);

            Host host1 = new Host("Nikola", "Serafimov", countries.get(0));
            host1.setUser(hostUser1);
            hostUser1.setHost(host1);

            Host host2 = new Host("Felix", "Schneider", countries.get(1));
            host2.setUser(hostUser2);
            hostUser2.setHost(host2);

            Host host3 = new Host("John", "Matthews", countries.get(2));
            host3.setUser(hostUser3);
            hostUser3.setHost(host3);

            hostRepository.saveAll(List.of(host1, host2, host3));
        } else {
            users = userRepository.findAll();
        }

        hosts = new ArrayList<>();
        if (this.hostRepository.count() == 0) {
            hosts.add(new Host("Nikola", "Serafimov", countries.get(0)));
            hosts.add(new Host("Felix", "Schneider", countries.get(1)));
            hosts.add(new Host("John", "Matthews", countries.get(2)));
            this.hostRepository.saveAll(hosts);
        } else {
            hosts = hostRepository.findAll();
        }

        accommodations = new ArrayList<>();
        if (this.accommodationRepository.count() == 0) {
            accommodations.add(new Accommodation("Luxury Villa", Category.HOUSE, hosts.get(0), 5));
            accommodations.add(new Accommodation("Lake view Apartment", Category.APARTMENT, hosts.get(1), 3));
            accommodations.add(new Accommodation("Cozy Mountain Motel", Category.MOTEL, hosts.get(0), 20));
            accommodations.add(new Accommodation("Downtown Studio", Category.FLAT, hosts.get(2), 1));
            accommodations.add(new Accommodation("Grand Plaza Hotel", Category.HOTEL, hosts.get(1), 100));
            this.accommodationRepository.saveAll(accommodations);
        } else {
            accommodations = accommodationRepository.findAll();
        }

        reviews = new ArrayList<>();
        if (this.reviewRepository.count() == 0) {
            reviews.add(new Review("Amazing place with a stunning view!", 5, accommodations.get(0)));
            reviews.add(new Review("Cozy and clean, perfect for a weekend getaway.", 4, accommodations.get(1)));
            reviews.add(new Review("The host was very friendly and helpful!", 5, accommodations.get(3)));
            reviews.add(new Review("Decent stay, but the rooms were smaller than expected.", 3, accommodations.get(2)));
            reviews.add(new Review("Loved the location, right in the city center.", 4, accommodations.get(4)));
            reviews.add(new Review("The WiFi was slow, but everything else was great.", 3, accommodations.get(1)));
            reviews.add(new Review("Super clean and well-equipped. Will book again!", 5, accommodations.get(4)));
            reviews.add(new Review("Noisy neighbors, but otherwise a nice stay.", 3, accommodations.get(3)));
            reviews.add(new Review("The best vacation rental I've stayed in!", 5, accommodations.get(0)));
            reviews.add(new Review("Not worth the price. Needs better maintenance.", 2, accommodations.get(2)));
            this.reviewRepository.saveAll(reviews);
        } else {
            reviews = reviewRepository.findAll();
        }
    }
}
