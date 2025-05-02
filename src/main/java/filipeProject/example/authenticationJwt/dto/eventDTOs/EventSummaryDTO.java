package filipeProject.example.authenticationJwt.dto.eventDTOs;

import filipeProject.example.authenticationJwt.entities.Event;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Instant;

@Getter
@AllArgsConstructor
public class EventSummaryDTO {
    private Long id;
    private String name;
    private String description;
    private Instant startDate;
    private Instant endDate;
    private String place;

    public EventSummaryDTO(Event entity){
        id = entity.getId();
        name = entity.getName();
        description = entity.getDescription();
        startDate = entity.getStartDate();
        endDate = entity.getEndDate();
        place = entity.getPlace();
    }
}
