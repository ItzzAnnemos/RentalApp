package mk.ukim.finki.emt.rentalapp.events;

import lombok.Getter;
import mk.ukim.finki.emt.rentalapp.model.domain.Host;
import org.springframework.context.ApplicationEvent;

import java.time.LocalDateTime;

@Getter
public class HostDeletedEvent extends ApplicationEvent {

    private LocalDateTime when;

    public HostDeletedEvent(Host source) {
        super(source);
    }

    public HostDeletedEvent(Host source, LocalDateTime when) {
        super(source);
        this.when = when;
    }
}
