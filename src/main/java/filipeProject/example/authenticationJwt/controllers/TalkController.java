package filipeProject.example.authenticationJwt.controllers;

import filipeProject.example.authenticationJwt.service.TalkService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/talks")
public class TalkController {

    private final TalkService service;

    public TalkController(TalkService service) {
        this.service = service;
    }

}
