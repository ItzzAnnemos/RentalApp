package mk.ukim.finki.emt.rentalapp.events;

import lombok.Getter;
import mk.ukim.finki.emt.rentalapp.model.domain.Host;
import org.springframework.context.ApplicationEvent;

import java.time.LocalDateTime;

@Getter
public class HostCreatedEvent extends ApplicationEvent {

    private LocalDateTime when;


    public HostCreatedEvent(Host source) {
        super(source);
    }

    public HostCreatedEvent(Host source, LocalDateTime when) {
        super(source);
        this.when = when;
    }
}
