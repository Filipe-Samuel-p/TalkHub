package filipeProject.example.authenticationJwt.dto.userDTOs;


import filipeProject.example.authenticationJwt.entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserProfileDTO {

    private UUID id;
    private String name;
    private String email;
    private Instant creationDate;
    private String biography;
    private String imgUrl;
    private String imgBackground;
    private int numFollowers;
    private int numFollowing;


    public UserProfileDTO(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.name = user.getName();
        this.biography = user.getBiography();
        this.creationDate = user.getCreationDate();
        this.imgUrl = user.getImgUrl();
        this.imgBackground = user.getImgBackground();
        this.numFollowers = user.getNumFollowers();
        this.numFollowing = user.getNumFollowing();
    }
}
