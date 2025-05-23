package filipeProject.example.authenticationJwt.controllers;

import filipeProject.example.authenticationJwt.dto.postDTOs.PostWithoutUserDTO;
import filipeProject.example.authenticationJwt.service.FeedService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/feed")
public class FeedController {

    private final FeedService service;

    public FeedController(FeedService service) {
        this.service = service;
    }

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<PostWithoutUserDTO> newPost (@RequestBody PostWithoutUserDTO dto, JwtAuthenticationToken token){
        var post = service.newPost(dto,token);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(post);
    }
}
