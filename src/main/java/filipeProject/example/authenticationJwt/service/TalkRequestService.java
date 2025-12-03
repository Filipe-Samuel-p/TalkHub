package filipeProject.example.authenticationJwt.service;

import filipeProject.example.authenticationJwt.entities.Talk;
import filipeProject.example.authenticationJwt.entities.TalkRequest;
import filipeProject.example.authenticationJwt.enums.RequestStatus;
import filipeProject.example.authenticationJwt.exceptions.ConflictException;
import filipeProject.example.authenticationJwt.exceptions.ResourceNotFoundException;
import filipeProject.example.authenticationJwt.repositories.*;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
public class TalkRequestService {

  /*  private final TalkRequestRepository repository;
    private final CategoryRepository categoryRepository;
    private final EventRepository eventRepository;
    private final SpeakerRepository speakerRepository;
    private final TalkRepository talkRepository;

    public TalkRequestService(TalkRequestRepository repository, CategoryRepository categoryRepository, EventRepository eventRepository, SpeakerRepository speakerRepository, TalkRepository talkRepository) {
        this.repository = repository;
        this.categoryRepository = categoryRepository;
        this.eventRepository = eventRepository;
        this.speakerRepository = speakerRepository;
        this.talkRepository = talkRepository;
    }

    public TalkRequestDTO createNewTalkRequest(TalkRequestDTO dto, JwtAuthenticationToken token){

        var entity = new TalkRequest();
        dtoToEntity(dto,entity,token);
        entity = repository.save(entity);
        return new TalkRequestDTO(entity);

    }

    public List<TalkRequestDTO> allTalkRequestPending() {
        return repository.findAll().stream()
                .filter(talkRequest -> talkRequest.getStatus().equals(RequestStatus.PENDING))
                .map(talkRequest -> new TalkRequestDTO(talkRequest))
                .toList();
    }

    public void approveTalkRequest(Long id){

        var talkRequest = repository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Solicitação não encontrada"));

        if(talkRequest.getStatus().equals(RequestStatus.APPROVED) || talkRequest.getStatus().equals(RequestStatus.DENIED)){
            throw new ConflictException("Essa solicitação ja foi respondida");
        }

        var associatedEvent = eventRepository.findById(talkRequest.getEventId())
                .orElseThrow(()-> new ResourceNotFoundException("Evento associado não encontrado"));

        var associatedCategory = categoryRepository.findById(talkRequest.getCategoryId())
                .orElseThrow(()-> new ResourceNotFoundException("Categoria associada não encontrada"));

        var associatedSpeaker = speakerRepository.findById(talkRequest.getSpeakerId())
                .orElseThrow(()-> new ResourceNotFoundException("Palestrante associado não encontrado"));

        var newTalk = new Talk(talkRequest);
        newTalk.setEvent(associatedEvent);
        newTalk.setSpeaker(associatedSpeaker);
        newTalk.setCategory(associatedCategory);
        talkRepository.save(newTalk);

        talkRequest.setStatus(RequestStatus.APPROVED);
        repository.save(talkRequest);

    }

    public void deniedTalkRequest(Long talkId){

        var talkRequest = repository.findById(talkId)
                .orElseThrow(()-> new ResourceNotFoundException("Solicitação não encontrada"));

        if(talkRequest.getStatus().equals(RequestStatus.APPROVED) || talkRequest.getStatus().equals(RequestStatus.DENIED)){
            throw new ConflictException("Essa solicitação ja foi respondida");
        }

        talkRequest.setStatus(RequestStatus.DENIED);
        repository.save(talkRequest);

    }

    private void dtoToEntity(TalkRequestDTO dto, TalkRequest entity, JwtAuthenticationToken token){

        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setDuration(dto.getDuration());
        entity.setEventId(dto.getEventId());
        entity.setDifficultyLevel(dto.getDifficultyLevel());
        entity.setNumberAvailable(dto.getNumberAvailable());
        entity.setLocal(dto.getLocal());
        entity.setStartTime(dto.getStartTime());
        entity.setSpeakerId(UUID.fromString(token.getName()));
        entity.setCategoryId(dto.getCategoryId());
        entity.setStatus(RequestStatus.PENDING);

    } */
}
