package filipeProject.example.authenticationJwt.controllers;

import filipeProject.example.authenticationJwt.dto.registrationDTOs.RegistrationDTO;
import filipeProject.example.authenticationJwt.dto.talkDTOs.TalkDTO;
import filipeProject.example.authenticationJwt.service.TalkService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/talk")
public class TalkController {

    private final TalkService service;

    public TalkController(TalkService service) {
        this.service = service;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<TalkDTO> getTalk(@PathVariable Long id) {
        var dto = service.getTalk(id);
        return ResponseEntity.ok(dto);
    }

    @PatchMapping(value = "/{id}")
    @PreAuthorize("hasRole('SPEAKER') or hasRole('ADMIN')")
    public ResponseEntity<TalkDTO> updateTalk(@PathVariable Long id,
                                              @RequestBody TalkDTO talkDTO,JwtAuthenticationToken token){
        var dto = service.updateTalk(id,talkDTO,token);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasRole('SPEAKER') or hasRole('ADMIN')")
    public ResponseEntity<Void> updateTalk(@PathVariable Long id,
                                              JwtAuthenticationToken token){
        service.deleteTalk(id,token);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "/{id}/register")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<RegistrationDTO> newRegistration(@PathVariable Long id,JwtAuthenticationToken token){
        var dto = service.newRegistration(id,token);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PostMapping(value = "/{id}/approve")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> approveTalk(@PathVariable Long id){
        service.approveTalk(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "/{id}/denied")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deniedSpeaker(@PathVariable Long id){
        service.deniedTalk(id);
        return ResponseEntity.noContent().build();
    }



}
