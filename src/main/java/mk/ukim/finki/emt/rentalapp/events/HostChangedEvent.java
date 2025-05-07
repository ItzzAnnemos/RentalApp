package mk.ukim.finki.emt.rentalapp.events;

import lombok.Getter;
import mk.ukim.finki.emt.rentalapp.model.domain.Host;
import org.springframework.context.ApplicationEvent;

import java.time.LocalDateTime;

@Getter
public class HostChangedEvent extends ApplicationEvent {

    private LocalDateTime when;

    public HostChangedEvent(Host source) {
        super(source);
    }

    public HostChangedEvent(Host source, LocalDateTime when) {
        super(source);
        this.when = when;
    }
}
