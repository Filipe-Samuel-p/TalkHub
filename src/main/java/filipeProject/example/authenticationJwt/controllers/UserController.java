package filipeProject.example.authenticationJwt.controllers;

import filipeProject.example.authenticationJwt.dto.userDTOs.UpdateUserDTO;
import filipeProject.example.authenticationJwt.dto.userDTOs.UserProfileDTO;
import filipeProject.example.authenticationJwt.dto.userDTOs.UserRegisterDTO;
import filipeProject.example.authenticationJwt.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    private UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<UserRegisterDTO> newUser(@RequestBody UserRegisterDTO dto){

        dto = service.newUser(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @GetMapping(value = "/profile/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<UserProfileDTO> userProfile(@PathVariable UUID id){
        var profile = service.userProfile(id);
        return ResponseEntity.ok(profile);
    }

    @PutMapping(value = "/me")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<UpdateUserDTO> updateUser (@RequestBody UpdateUserDTO dto, JwtAuthenticationToken token){
        var user = service.updateUser(dto,token);
        return ResponseEntity.ok(user);
    }

    @GetMapping(value = "/me")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<UserProfileDTO> personalProfile (JwtAuthenticationToken token){
        var id = UUID.fromString(token.getName());
        var profile = service.userProfile(id);
        return ResponseEntity.ok(profile);
    }

}
