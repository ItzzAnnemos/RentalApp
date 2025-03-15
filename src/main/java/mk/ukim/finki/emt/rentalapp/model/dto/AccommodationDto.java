package mk.ukim.finki.emt.rentalapp.model.dto;

import lombok.Data;
import mk.ukim.finki.emt.rentalapp.model.enumerations.Category;

@Data
public class AccommodationDto {
    private String name;

    private Category category;

    private Long host;

    private Integer numRooms;

    public AccommodationDto() {
    }

    public AccommodationDto(String name, Category category, Long host, Integer numRooms) {
        this.name = name;
        this.category = category;
        this.host = host;
        this.numRooms = numRooms;
    }
}
