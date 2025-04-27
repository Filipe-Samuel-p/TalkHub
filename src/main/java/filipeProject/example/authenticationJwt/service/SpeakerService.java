package filipeProject.example.authenticationJwt.service;

import filipeProject.example.authenticationJwt.repositories.SpeakerRepository;
import org.springframework.stereotype.Service;

@Service
public class SpeakerService {

    private final SpeakerRepository repository;

    public SpeakerService(SpeakerRepository repository) {
        this.repository = repository;
    }



}
