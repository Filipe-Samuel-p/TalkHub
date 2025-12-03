package filipeProject.example.authenticationJwt.dto.userDTOs;

import com.fasterxml.jackson.annotation.JsonIgnore;
import filipeProject.example.authenticationJwt.entities.User;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

