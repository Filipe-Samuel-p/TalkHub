package filipeProject.example.authenticationJwt.controllers;

import filipeProject.example.authenticationJwt.dto.talkDTOs.TalkDTO;
import filipeProject.example.authenticationJwt.dto.talkDTOs.TalkRequestDTO;
import filipeProject.example.authenticationJwt.service.TalkService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

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
}
