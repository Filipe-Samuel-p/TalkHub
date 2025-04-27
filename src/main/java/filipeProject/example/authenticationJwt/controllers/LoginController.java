package filipeProject.example.authenticationJwt.controllers;


import filipeProject.example.authenticationJwt.dto.loginDTOs.LoginRequestDTO;
import filipeProject.example.authenticationJwt.dto.loginDTOs.LoginResponseDTO;
import filipeProject.example.authenticationJwt.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/login")
public class LoginController {

    private AuthService service;

    public LoginController(AuthService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO dto){
        var response = service.login(dto);
        return ResponseEntity.ok(response);
    }


}
