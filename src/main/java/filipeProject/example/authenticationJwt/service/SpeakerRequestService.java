package filipeProject.example.authenticationJwt.service;

import filipeProject.example.authenticationJwt.dto.speakerDTOs.SpeakerRequestDTO;
import filipeProject.example.authenticationJwt.entities.SpeakerRequest;
import filipeProject.example.authenticationJwt.enums.RoleName;
import filipeProject.example.authenticationJwt.exceptions.ConflictException;
import filipeProject.example.authenticationJwt.exceptions.ResourceNotFoundException;
import filipeProject.example.authenticationJwt.repositories.RoleRepository;
import filipeProject.example.authenticationJwt.repositories.SpeakerRequestRepository;
import filipeProject.example.authenticationJwt.repositories.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
public class SpeakerRequestService {

    private final SpeakerRequestRepository repository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public SpeakerRequestService(SpeakerRequestRepository repository, UserRepository userRepository, RoleRepository roleRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
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
        newRequest.setApproved(false);
        newRequest.setRequestDate(Instant.now());

        repository.save(newRequest);

    }

    public Page<SpeakerRequestDTO> allRequestsToBeSpeaker(Pageable pageable){
        var allRequests = repository.findAll(pageable);
        return allRequests
                .map(SpeakerRequestDTO::new);
    }
}
