package filipeProject.example.authenticationJwt.service;

import filipeProject.example.authenticationJwt.dto.speakerDTOs.SpeakerRequestDTO;
import filipeProject.example.authenticationJwt.entities.Speaker;
import filipeProject.example.authenticationJwt.entities.SpeakerRequest;
import filipeProject.example.authenticationJwt.enums.RoleName;
import filipeProject.example.authenticationJwt.enums.SpeakerRequestStatus;
import filipeProject.example.authenticationJwt.exceptions.ConflictException;
import filipeProject.example.authenticationJwt.exceptions.ResourceNotFoundException;
import filipeProject.example.authenticationJwt.repositories.RoleRepository;
import filipeProject.example.authenticationJwt.repositories.SpeakerRepository;
import filipeProject.example.authenticationJwt.repositories.SpeakerRequestRepository;
import filipeProject.example.authenticationJwt.repositories.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.UUID;

@Service
public class SpeakerRequestService {

    private final SpeakerRequestRepository repository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final SpeakerRepository speakerRepository;

    public SpeakerRequestService(SpeakerRequestRepository repository, UserRepository userRepository, RoleRepository roleRepository, SpeakerRepository speakerRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.speakerRepository = speakerRepository;
    }

    public void requestToBeSpeaker(JwtAuthenticationToken token){

        var userId = UUID.fromString(token.getName());
        var user = userRepository.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException("Usuário não encontrado"));

        var existingRequest = repository.findByUser(user);
        var isRoleSpeaker = user.getRoles().contains((roleRepository.findByAuthority(RoleName.ROLE_SPEAKER)));

        if(existingRequest.isPresent() || isRoleSpeaker){
            throw new ConflictException("Esta solicitação ja foi feita ou este usuário já é um palestrante");
        }


        var newRequest = new SpeakerRequest();
        newRequest.setUser(user);
        newRequest.setStatus(SpeakerRequestStatus.PENDING);
        newRequest.setRequestDate(Instant.now());

        repository.save(newRequest);

    }

    public Page<SpeakerRequestDTO> allRequestsToBeSpeaker(Pageable pageable){
        var allRequests = repository.findAll(pageable);
        return allRequests
                .map(SpeakerRequestDTO::new);
    }

    @Transactional
    public void approveSpeakerRequest (UUID requestId){

        var roleSpeaker = roleRepository.findByAuthority(RoleName.ROLE_SPEAKER);

        var request = repository.findById(requestId)
                .orElseThrow(()-> new ResourceNotFoundException("Requisição não encontrada"));

        var user = request.getUser();
        var isRoleSpeaker = user.getRoles().contains(roleSpeaker);
        var isApproved = request.getStatus().equals(SpeakerRequestStatus.APPROVED);

        if(isApproved || isRoleSpeaker){
            throw new ConflictException("Esta requisição já foi aprovada ou o usuário ja é um palestrante");
        }

        var newSpeaker = new Speaker();
        newSpeaker.setUser(user);

        request.setStatus(SpeakerRequestStatus.APPROVED);

        user.getRoles().add(roleSpeaker);

        userRepository.save(user);
        speakerRepository.save(newSpeaker);
        repository.save(request);

    }

    @Transactional
    public void deniedSpeakerRequest(UUID requestId){

        var roleSpeaker = roleRepository.findByAuthority(RoleName.ROLE_SPEAKER);

        var request = repository.findById(requestId)
                .orElseThrow(()-> new ResourceNotFoundException("Requisição não encontrada"));

        var user = request.getUser();
        var isRoleSpeaker = user.getRoles().contains(roleSpeaker);
        var isApproved = request.getStatus().equals(SpeakerRequestStatus.DENIED);

        if(isApproved || isRoleSpeaker){
            throw new ConflictException("Esta requisição já foi NEGADA ou o usuário ja é um palestrante");
        }

        request.setStatus(SpeakerRequestStatus.DENIED);

        repository.save(request);
    }
}
