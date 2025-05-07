package mk.ukim.finki.emt.rentalapp.listeners;

import mk.ukim.finki.emt.rentalapp.events.HostChangedEvent;
import mk.ukim.finki.emt.rentalapp.events.HostCreatedEvent;
import mk.ukim.finki.emt.rentalapp.events.HostDeletedEvent;
import mk.ukim.finki.emt.rentalapp.service.domain.HostService;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class HostEventHandlers {
    private final HostService hostService;

    public HostEventHandlers(HostService hostService) {
        this.hostService = hostService;
    }

    @EventListener
    public void onHostCreated(HostCreatedEvent event) {
        hostService.refreshMaterializedView();
    }

    @EventListener
    public void onHostChanged(HostChangedEvent event) {
        hostService.refreshMaterializedView();
    }

    @EventListener
    public void onHostDeleted(HostDeletedEvent event) {
        hostService.refreshMaterializedView();
    }
}
