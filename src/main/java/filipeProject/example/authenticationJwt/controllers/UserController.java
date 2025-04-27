package filipeProject.example.authenticationJwt.controllers;

import filipeProject.example.authenticationJwt.dto.userDTOs.FollowerAndFollowingDTO;
import filipeProject.example.authenticationJwt.dto.userDTOs.UpdateUserDTO;
import filipeProject.example.authenticationJwt.dto.userDTOs.UserProfileDTO;
import filipeProject.example.authenticationJwt.dto.userDTOs.UserRegisterDTO;
import filipeProject.example.authenticationJwt.service.UserService;
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
@RequestMapping(value = "/users")
public class UserController {

    private final UserService service;

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

    @GetMapping(value = "{id}/profile")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<UserProfileDTO> userProfile(@PathVariable UUID id){
        var profile = service.userProfile(id);
        return ResponseEntity.ok(profile);
    }

    @DeleteMapping(value = {"/{id}"})
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteUser (@PathVariable UUID id){
        service.deleteUser(id);
        return ResponseEntity.noContent().build();
    }


    @PostMapping(value = "/{id}/follow")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Void> addNewFollower(@PathVariable UUID id, JwtAuthenticationToken token){

        service.addNewFollower(id, token);
        return ResponseEntity.noContent().build();

    }

    @DeleteMapping(value = {"/{id}/unfollow"})
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Void> deleteFollower(@PathVariable UUID id, JwtAuthenticationToken token){
        service.deleteFollower(id,token);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/followers")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<Page<FollowerAndFollowingDTO>> getUserFollowers(@PathVariable UUID id, Pageable pageable) {
        var page = service.allUserFollowers(id, pageable);
        return ResponseEntity.ok(page);
    }

    @GetMapping(value = "/me")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<UserProfileDTO> personalProfile (JwtAuthenticationToken token){
        var id = UUID.fromString(token.getName());
        var profile = service.userProfile(id);
        return ResponseEntity.ok(profile);
    }

    @PutMapping(value = "/me")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<UpdateUserDTO> updateUser (@RequestBody UpdateUserDTO dto, JwtAuthenticationToken token){
        var user = service.updateUser(dto,token);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping(value = {"/me"})
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Void> deletePersonalAccount (JwtAuthenticationToken token){
        UUID id = UUID.fromString(token.getName());
        service.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/me/followers")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<Page<FollowerAndFollowingDTO>> getMyFollowers(JwtAuthenticationToken token, Pageable pageable) {
        var page = service.getLoggedUserFollowers(token, pageable);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/me/following")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<Page<FollowerAndFollowingDTO>> getMyFollowing(JwtAuthenticationToken token, Pageable pageable) {
        var page = service.getLoggedUserFollowing(token, pageable);
        return ResponseEntity.ok(page);
    }

}
