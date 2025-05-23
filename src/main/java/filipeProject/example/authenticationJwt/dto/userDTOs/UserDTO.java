package filipeProject.example.authenticationJwt.dto.userDTOs;

import filipeProject.example.authenticationJwt.entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UserDTO{

    private UUID id;
    private String name;
    private String biography;
    private String imgUrl;
    private String imgBackground;
    private int numFollowers;
    private int numFollowing;

    public UserDTO(User user){
        id = user.getId();
        name = user.getName();
        biography = user.getBiography();
        imgUrl = user.getImgUrl();
        imgBackground = user.getImgBackground();
        numFollowers = user.getNumFollowers();
        numFollowing = user.getNumFollowing();
    }
}