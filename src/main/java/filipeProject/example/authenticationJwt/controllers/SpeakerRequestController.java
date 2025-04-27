package filipeProject.example.authenticationJwt.controllers;

import filipeProject.example.authenticationJwt.dto.speakerDTOs.SpeakerRequestDTO;
import filipeProject.example.authenticationJwt.service.SpeakerRequestService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(value = "/speaker-request")
public class SpeakerRequestController {

    private final SpeakerRequestService service;

    public SpeakerRequestController(SpeakerRequestService service) {
        this.service = service;
    }

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Void> requestToBeSpeaker(JwtAuthenticationToken token){
        service.requestToBeSpeaker(token);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Page<SpeakerRequestDTO>> allRequestsToBeSpeaker(Pageable pageable){
        var allRequests = service.allRequestsToBeSpeaker(pageable);
        return ResponseEntity.ok(allRequests);
    }

    @PatchMapping(value = "/{id}/approve")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> approveSpeakerRequest(@PathVariable UUID id){
        service.approveSpeakerRequest(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping(value = "/{id}/denied")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deniedSpeakerRequest(@PathVariable UUID id){
        service.deniedSpeakerRequest(id);
        return ResponseEntity.noContent().build();
    }

}
