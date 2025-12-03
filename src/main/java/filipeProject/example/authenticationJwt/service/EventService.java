package filipeProject.example.authenticationJwt.service;

import filipeProject.example.authenticationJwt.dto.eventDTOs.EventDTO;
import filipeProject.example.authenticationJwt.dto.eventDTOs.EventSummaryDTO;
import filipeProject.example.authenticationJwt.dto.talkDTOs.TalkCreationDTO;

import filipeProject.example.authenticationJwt.entities.*;
import filipeProject.example.authenticationJwt.enums.RequestStatus;
import filipeProject.example.authenticationJwt.exceptions.ResourceNotFoundException;
import filipeProject.example.authenticationJwt.repositories.EventRepository;
import filipeProject.example.authenticationJwt.repositories.SpeakerRepository;
import filipeProject.example.authenticationJwt.repositories.TalkRepository;
import filipeProject.example.authenticationJwt.repositories.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class EventService {

    private final EventRepository repository;
    private final TalkRepository talkRepository;
    private final SpeakerRepository speakerRepository;

    public EventService(EventRepository repository, TalkRepository talkRepository, UserRepository userRepository, SpeakerRepository speakerRepository) {
        this.repository = repository;
        this.talkRepository = talkRepository;
        this.speakerRepository = speakerRepository;
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


    public TalkCreationDTO newTalk(TalkCreationDTO dto, JwtAuthenticationToken token, Long eventID){

        var talkEntity = new Talk();
        auxTalkDtoToTalk(dto,talkEntity,token,eventID);
        talkEntity = talkRepository.save(talkEntity);
        return new TalkCreationDTO(talkEntity);

    }


    private void auxTalkDtoToTalk(TalkCreationDTO dto, Talk entity, JwtAuthenticationToken token, Long eventID){

        var speakerId = UUID.fromString(token.getName());
        var speaker = speakerRepository.findById(speakerId)
                .orElseThrow(()-> new ResourceNotFoundException("Palestrante não encontrado"));

        var event = repository.findById(eventID)
                        .orElseThrow(()-> new ResourceNotFoundException("Palestra não encontrada"));

        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setStartTime(dto.getStartTime());
        entity.setDuration(dto.getDuration());
        entity.setTotalCapacity(dto.getTotalCapacity());
        entity.setNumberAvailable(dto.getNumberAvailable());
        entity.setLocal(dto.getLocal());
        entity.setDifficultyLevel(dto.getDifficultyLevel());
        entity.setApprovalStatus(RequestStatus.PENDING);
        entity.setSpeaker(speaker);
        entity.setEvent(event);
    }

    private void eventDTOToEntity(EventDTO dto, Event entity){
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setStartDate(dto.getStartDate());
        entity.setEndDate(dto.getEndDate());
        entity.setPlace(dto.getPlace());
    }


}
