package filipeProject.example.authenticationJwt.service;

import filipeProject.example.authenticationJwt.dto.registrationDTOs.RegistrationDTO;
import filipeProject.example.authenticationJwt.dto.talkDTOs.TalkDTO;
import filipeProject.example.authenticationJwt.entities.Registration;
import filipeProject.example.authenticationJwt.entities.Talk;
import filipeProject.example.authenticationJwt.enums.Payment;
import filipeProject.example.authenticationJwt.enums.RequestStatus;
import filipeProject.example.authenticationJwt.exceptions.AccessDeniedException;
import filipeProject.example.authenticationJwt.exceptions.ConflictException;
import filipeProject.example.authenticationJwt.exceptions.DataIntegrityViolationException;
import filipeProject.example.authenticationJwt.exceptions.ResourceNotFoundException;
import filipeProject.example.authenticationJwt.repositories.*;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
public class TalkService {

    private final TalkRepository repository;
    private final UserRepository userRepository;
    private final RegistrationRepository registrationRepository;


    public TalkService(TalkRepository repository, UserRepository userRepository, RegistrationRepository registrationRepository, SpeakerRepository speakerRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
        this.registrationRepository = registrationRepository;

    }

    public TalkDTO getTalk(Long talkId){

        var talkEntity = repository.findById(talkId)
                .orElseThrow(()-> new ResourceNotFoundException("Palestra não encontrada"));

        return new TalkDTO(talkEntity);

    }

    public TalkDTO updateTalk(Long talkId, TalkDTO dto, JwtAuthenticationToken token){

        var roles = token.getAuthorities()
                .stream()
                .map(Object::toString)
                .toList();

        var loggedUserId = UUID.fromString(token.getName());

        var talkEntity = repository.findById(talkId)
                .orElseThrow(()-> new ResourceNotFoundException("Palestra não encontrada"));

        var isAdmin = roles.contains("ROLE_ADMIN");
        var isTheOfficialSpeaker = talkEntity.getSpeaker().getUserId().equals(loggedUserId);

        if (!isAdmin && !isTheOfficialSpeaker) {
            throw new AccessDeniedException("O usuário não tem permissão para atualizar esta palestra");
        }

        if (dto.getTitle() != null) {
            talkEntity.setTitle(dto.getTitle());
        }

        if (dto.getDescription() != null) {
            talkEntity.setDescription(dto.getDescription());
        }

        if (dto.getStartTime() != null) {
            talkEntity.setStartTime(dto.getStartTime());
        }

        if (dto.getDuration() != null) {
            talkEntity.setDuration(dto.getDuration());
        }

        if (dto.getTotalCapacity() != 0) {
            talkEntity.setTotalCapacity(dto.getTotalCapacity());
        }


        if (dto.getLocal() != null) {
            talkEntity.setLocal(dto.getLocal());
        }

        if (dto.getDifficultyLevel() != null) {
            talkEntity.setDifficultyLevel(dto.getDifficultyLevel());
        }

        repository.save(talkEntity);

        return new TalkDTO(talkEntity);

    }

    public void deleteTalk(Long talkId, JwtAuthenticationToken token){

        var roles = token.getAuthorities()
                .stream()
                .map(Object::toString)
                .toList();

        UUID loggedUserId = UUID.fromString(token.getName());

        var talkEntity = repository.findById(talkId)
                .orElseThrow(()-> new ResourceNotFoundException("Palestra não encontrada"));

        var isAdmin = roles.contains("ROLE_ADMIN");
        var isTheOfficialSpeaker = talkEntity.getSpeaker().getUserId().equals(loggedUserId);

        if (!isAdmin && !isTheOfficialSpeaker) {
            throw new AccessDeniedException("O usuário não tem permissão para atualizar esta palestra");
        }

        try {
            repository.deleteById(talkId);
            // A deleção funciona mesmo com entidades associadas porque os campos relacionados
            // (event, speaker, category) são representados como chaves estrangeiras (foreign keys)
            // que permitem valores nulos (nullable) no banco de dados.
            // Isso significa que o banco aceita que a Talk seja deletada sem exigir a exclusão das entidades relacionadas.
            // A JPA também não está configurada para deletar em cascata (sem CascadeType.REMOVE).

        } catch (DataIntegrityViolationException e) {
            throw new ConflictException("Falha de integridade referencial");
        }
    }


    public RegistrationDTO newRegistration(Long talkId, JwtAuthenticationToken token){

        var loggedInUserId = UUID.fromString(token.getName());

        var talk = repository.findById(talkId)
                .orElseThrow(()-> new ResourceNotFoundException("Palestra não encontrada"));

        var loggedInUser = userRepository.findById(loggedInUserId)
                .orElseThrow(()-> new ResourceNotFoundException("Usuário logado não encontrado"));

        var isTheSpeaker = talk.getSpeaker().getUserId().equals(loggedInUserId);

        if(isTheSpeaker){
            throw new ConflictException("O usuário logado é o responsável pela palestra");
        }

        var allUserRegistrations = loggedInUser.getRegistrations();
        for (Registration registration:allUserRegistrations){
            if(registration.getTalk().equals(talk)){
                throw new ConflictException("O usuário logado ja fez a inscrição para esta palestra");
            }
        }

        var newRegistration = new Registration();

        newRegistration.setRegistrationDate(Instant.now());
        newRegistration.setPaymentStatus(Payment.PENDING);
        newRegistration.setUser(loggedInUser);
        newRegistration.setTalk(talk);
        registrationRepository.save(newRegistration);

        return new RegistrationDTO(newRegistration);

    }

    public void approveTalk(Long talkID){

        var talk = repository.findById(talkID)
                .orElseThrow(()-> new ResourceNotFoundException("Palestra não encontrada"));


        if(talk.getApprovalStatus().equals(RequestStatus.APPROVED) || talk.getApprovalStatus().equals(RequestStatus.DENIED)){
            throw new ConflictException("Essa solicitação ja foi respondida");
        }


        talk.setApprovalStatus(RequestStatus.APPROVED);
        var hour = Instant.now();
        talk.setRequest_response_date(hour);

        repository.save(talk);

    }

    public void deniedTalk(Long talkID){

        var talk = repository.findById(talkID)
                .orElseThrow(()-> new ResourceNotFoundException("Solicitação não encontrada"));

        if(talk.getApprovalStatus().equals(RequestStatus.APPROVED) || talk.getApprovalStatus().equals(RequestStatus.DENIED)){
            throw new ConflictException("Essa solicitação ja foi respondida");
        }

        talk.setApprovalStatus(RequestStatus.DENIED);
        repository.save(talk);

    }




}
