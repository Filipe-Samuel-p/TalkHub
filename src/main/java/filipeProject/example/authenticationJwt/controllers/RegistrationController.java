package filipeProject.example.authenticationJwt.controllers;

import filipeProject.example.authenticationJwt.dto.registrationDTOs.RegistrationDTO;
import filipeProject.example.authenticationJwt.service.RegistrationService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/registrations")
public class RegistrationController {

    private final RegistrationService service;

    public RegistrationController(RegistrationService service) {
        this.service = service;
    }

    @GetMapping(value = "/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<RegistrationDTO> getRegistration(@PathVariable Long id, JwtAuthenticationToken token){
        var dto = service.getRegistration(id, token);
        return ResponseEntity.ok(dto);
    }

    @PatchMapping(value = "/{id}/confirm")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Void> confirmRegister(@PathVariable Long id, JwtAuthenticationToken token){
        service.confirmRegister(id,token);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{id}/unregister")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Void> unregisterTalk(@PathVariable Long id, JwtAuthenticationToken token){
        service.unregisterTalk(id,token);
        return ResponseEntity.noContent().build();
    }
}
