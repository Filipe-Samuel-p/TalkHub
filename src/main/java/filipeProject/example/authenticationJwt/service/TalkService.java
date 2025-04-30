package filipeProject.example.authenticationJwt.service;

import filipeProject.example.authenticationJwt.repositories.TalkRepository;
import org.springframework.stereotype.Service;

@Service
public class TalkService {

    private final TalkRepository repository;

    public TalkService(TalkRepository repository) {
        this.repository = repository;
    }


}
