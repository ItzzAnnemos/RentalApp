package mk.ukim.finki.emt.rentalapp.jobs;

import mk.ukim.finki.emt.rentalapp.service.domain.AccommodationService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {
    private final AccommodationService accommodationService;

    public ScheduledTasks(AccommodationService accommodationService) {
        this.accommodationService = accommodationService;
    }

    @Scheduled(cron = "0 0 0 * * *")
    public void refreshMaterializedView() {
        accommodationService.refreshMaterializedView();
    }
}
