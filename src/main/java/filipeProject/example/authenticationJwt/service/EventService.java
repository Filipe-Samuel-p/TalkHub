package filipeProject.example.authenticationJwt.service;

import filipeProject.example.authenticationJwt.dto.eventDTOs.EventDTO;
import filipeProject.example.authenticationJwt.dto.eventDTOs.EventSummaryDTO;
import filipeProject.example.authenticationJwt.dto.userDTOs.UserRegisterDTO;
import filipeProject.example.authenticationJwt.entities.Event;
import filipeProject.example.authenticationJwt.entities.User;
import filipeProject.example.authenticationJwt.exceptions.ResourceNotFoundException;
import filipeProject.example.authenticationJwt.repositories.EventRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public EventDTO getEventById (Long id){
        var event = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Evento não encontrado"));
        return new EventDTO(event);
    }

    public Page<EventSummaryDTO> getAllEvents (Pageable pageable){
        var allEvents = repository.findAll(pageable);
        return allEvents.map(EventSummaryDTO::new);
    }

    private void eventDTOToEntity(EventDTO dto, Event entity){
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setStartDate(dto.getStartDate());
        entity.setEndDate(dto.getEndDate());
        entity.setPlace(dto.getPlace());
    }
}
