package filipeProject.example.authenticationJwt.controllers;

import filipeProject.example.authenticationJwt.dto.speakerDTOs.SpeakerDTO;
import filipeProject.example.authenticationJwt.service.SpeakerService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/speaker")
public class SpeakerController {

    private final SpeakerService service;

    public SpeakerController(SpeakerService service) {
        this.service = service;
    }

    @PutMapping(value = "/update")
    @PreAuthorize("hasRole('SPEAKER')")
    public ResponseEntity<SpeakerDTO> fillSpeakerData(@RequestBody SpeakerDTO dto,
                                                      JwtAuthenticationToken token){
        var speakerdto = service.fillSpeakerData(dto,token);
        return ResponseEntity.ok(speakerdto);
    }

    @GetMapping(value = "/list")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Page<SpeakerDTO>> allSpeakers(Pageable pageable){

        var allSpeakers = service.allSpeakers(pageable);
        return ResponseEntity.ok(allSpeakers);

    }
}
