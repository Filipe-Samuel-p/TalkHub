package filipeProject.example.authenticationJwt.controllers;

import filipeProject.example.authenticationJwt.dto.eventDTOs.EventDTO;
import filipeProject.example.authenticationJwt.dto.eventDTOs.EventSummaryDTO;
import filipeProject.example.authenticationJwt.dto.talkDTOs.TalkCreationDTO;
import filipeProject.example.authenticationJwt.dto.talkDTOs.TalkDTO;
import filipeProject.example.authenticationJwt.service.EventService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/event")
public class EventController {

    private final EventService service;

    public EventController(EventService service) {
        this.service = service;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<EventDTO> newEvent(@RequestBody EventDTO dto){

        dto = service.newEvent(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<EventDTO> getEventById(@PathVariable Long id){
        var event = service.getEventById(id);
        return ResponseEntity.ok(event);
    }

    @GetMapping(value = "/list")
    public ResponseEntity<Page<EventSummaryDTO>> getAllEvents(Pageable pageable){
        var allEvents = service.getAllEvents(pageable);
        return ResponseEntity.ok(allEvents);
    }

    @PostMapping("{eventId}/create-talk")
    @PreAuthorize("hasRole('SPEAKER')")
    public ResponseEntity<TalkCreationDTO> createNewTalk(@RequestBody TalkCreationDTO dto,@PathVariable Long eventId, JwtAuthenticationToken token){
        dto = service.newTalk(dto,token,eventId);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }



}
