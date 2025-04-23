package filipeProject.example.authenticationJwt.dto;

import filipeProject.example.authenticationJwt.entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserRegisterDTO {

    private UUID id;
    private String name;
    private String email;
    private String password;
    private String cpf;
    private String biography;

    public UserRegisterDTO(User user){
        id = user.getId();
        name = user.getName();
        email = user.getEmail();
        password = user.getPassword();
        cpf = user.getCpf();
        biography = user.getBiography();
    }

}

