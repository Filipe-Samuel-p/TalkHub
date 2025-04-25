package filipeProject.example.authenticationJwt.dto.userDTOs;

import filipeProject.example.authenticationJwt.entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UpdateUserDTO {

    private String name;
    private String email;
    private String biography;
    private String imgUrl;
    private String imgBackground;

    public UpdateUserDTO(User user){
        this.email = user.getEmail();
        this.name = user.getName();
        this.biography = user.getBiography();
        this.imgUrl = user.getImgUrl();
        this.imgBackground = user.getImgBackground();
    }

}
