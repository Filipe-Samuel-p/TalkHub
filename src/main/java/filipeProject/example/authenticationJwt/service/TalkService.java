package filipeProject.example.authenticationJwt.service;

import filipeProject.example.authenticationJwt.dto.talkDTOs.TalkDTO;
import filipeProject.example.authenticationJwt.dto.talkDTOs.TalkRequestDTO;
import filipeProject.example.authenticationJwt.enums.RoleName;
import filipeProject.example.authenticationJwt.exceptions.AccessDeniedException;
import filipeProject.example.authenticationJwt.exceptions.ConflictException;
import filipeProject.example.authenticationJwt.exceptions.DataIntegrityViolationException;
import filipeProject.example.authenticationJwt.exceptions.ResourceNotFoundException;
import filipeProject.example.authenticationJwt.repositories.*;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TalkService {

    private final TalkRepository repository;
    private final UserRepository userRepository;

    public TalkService(TalkRepository repository, UserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
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

        UUID loggedUserId = UUID.fromString(token.getName());

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

        if (dto.getNumberAvailable() != 0) {
            talkEntity.setNumberAvailable(dto.getNumberAvailable());
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
}
