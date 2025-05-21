package filipeProject.example.authenticationJwt.service;

import filipeProject.example.authenticationJwt.dto.talkDTOs.TalkDTO;
import filipeProject.example.authenticationJwt.dto.talkDTOs.TalkRequestDTO;
import filipeProject.example.authenticationJwt.exceptions.ResourceNotFoundException;
import filipeProject.example.authenticationJwt.repositories.CategoryRepository;
import filipeProject.example.authenticationJwt.repositories.EventRepository;
import filipeProject.example.authenticationJwt.repositories.TalkRepository;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class TalkService {

    private final TalkRepository repository;

    public TalkService(TalkRepository repository) {
        this.repository = repository;
    }

    public TalkDTO getTalk(Long talkId){

        var talkEntity = repository.findById(talkId)
                .orElseThrow(()-> new ResourceNotFoundException("Palestra não encontrada"));

        return new TalkDTO(talkEntity);

    }
}
