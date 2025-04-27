package filipeProject.example.authenticationJwt.service;

import filipeProject.example.authenticationJwt.dto.speakerDTOs.SpeakerDTO;
import filipeProject.example.authenticationJwt.exceptions.ResourceNotFoundException;
import filipeProject.example.authenticationJwt.repositories.SpeakerRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SpeakerService {

    private final SpeakerRepository repository;

    public SpeakerService(SpeakerRepository repository) {
        this.repository = repository;
    }


    public SpeakerDTO fillSpeakerData (SpeakerDTO dto,JwtAuthenticationToken token){

        var speakerId = UUID.fromString(token.getName());
        var speaker = repository.findById(speakerId)
                .orElseThrow(()-> new ResourceNotFoundException("Palestrante não encontrado"));

        speaker.setResume(dto.getResume());
        speaker.setInstitution(dto.getInstitution());
        speaker.setSpecialties(dto.getSpecialties());

        repository.save(speaker);

        return new SpeakerDTO(speaker);
    }

    public Page<SpeakerDTO> allSpeakers (Pageable pageable){
        var allSpeakers = repository.findAll(pageable);
        return allSpeakers.map(SpeakerDTO::new);
    }

}
