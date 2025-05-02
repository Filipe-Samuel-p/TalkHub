package filipeProject.example.authenticationJwt.dto.eventDTOs;

import filipeProject.example.authenticationJwt.entities.Event;
import filipeProject.example.authenticationJwt.entities.Talk;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class EventDTO {

    private Long id;
    private String name;
    private String description;
    private Instant startDate;
    private Instant endDate;
    private String place;

    public EventDTO(Event entity){
        id = entity.getId();
        name = entity.getName();
        description = entity.getDescription();
        startDate = entity.getStartDate();
        endDate = entity.getEndDate();
        place = entity.getPlace();
    }

}
