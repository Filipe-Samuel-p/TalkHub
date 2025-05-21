package filipeProject.example.authenticationJwt.controllers;

import filipeProject.example.authenticationJwt.dto.talkDTOs.TalkRequestDTO;
import filipeProject.example.authenticationJwt.dto.userDTOs.UserRegisterDTO;
import filipeProject.example.authenticationJwt.service.TalkRequestService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/talk-request")
public class TalkRequestController {

    private final TalkRequestService service;

    public TalkRequestController(TalkRequestService service) {
        this.service = service;
    }

    @PostMapping
    @PreAuthorize("hasRole('SPEAKER')")
    public ResponseEntity<TalkRequestDTO> createNewTalkRequest(@RequestBody TalkRequestDTO dto, JwtAuthenticationToken token){
        dto = service.createNewTalkRequest(dto,token);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @GetMapping(value = "/pending")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<TalkRequestDTO>> getTalkRequestPending(){
        var all = service.allTalkRequestPending();
        return ResponseEntity.ok(all);
    }

    @PostMapping(value = "/{id}/approve")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> approveTalkRequest(@PathVariable Long id){
        service.approveTalkRequest(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "/{id}/denied")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deniedSpeakerRequest(@PathVariable Long id){
        service.deniedTalkRequest(id);
        return ResponseEntity.noContent().build();
    }
}
