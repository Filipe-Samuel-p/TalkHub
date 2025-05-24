package filipeProject.example.authenticationJwt.controllers;

import filipeProject.example.authenticationJwt.dto.postDTOs.PostDTO;
import filipeProject.example.authenticationJwt.dto.postDTOs.PostWithoutUserDTO;
import filipeProject.example.authenticationJwt.dto.userDTOs.UserDTO;
import filipeProject.example.authenticationJwt.service.FeedService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

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

    @GetMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Page<PostDTO>> feed(JwtAuthenticationToken token, Pageable pageable){
        var all = service.getPostsFromFollowed(UUID.fromString(token.getName()),pageable);
        return ResponseEntity.ok(all);
    }
}
