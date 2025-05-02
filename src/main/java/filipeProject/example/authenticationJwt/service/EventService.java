package filipeProject.example.authenticationJwt.service;

import filipeProject.example.authenticationJwt.dto.eventDTOs.EventDTO;
import filipeProject.example.authenticationJwt.dto.userDTOs.UserRegisterDTO;
import filipeProject.example.authenticationJwt.entities.Event;
import filipeProject.example.authenticationJwt.entities.User;
import filipeProject.example.authenticationJwt.repositories.EventRepository;
import org.springframework.stereotype.Service;

@Service
public class EventService {

    private final EventRepository repository;

    public EventService(EventRepository repository) {
        this.repository = repository;
    }

    public EventDTO newEvent(EventDTO dto){

        var entity = new Event();
        eventDTOToEntity(dto,entity);
        entity = repository.save(entity);
        return new EventDTO(entity);


    }

    private void eventDTOToEntity(EventDTO dto, Event entity){
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setStartDate(dto.getStartDate());
        entity.setEndDate(dto.getEndDate());
        entity.setPlace(dto.getPlace());
    }
}
