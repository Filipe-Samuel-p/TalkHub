package filipeProject.example.authenticationJwt.dto.userDTOs;


import filipeProject.example.authenticationJwt.entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class FollowerAndFollowingDTO {

    private UUID id;
    private String name;
    private String imgUrl;
    private String imgBackground;
    private int numFollowers;
    private int numFollowing;

    public FollowerAndFollowingDTO(User user) {
        id = user.getId();
        name = user.getName();
        imgUrl = user.getImgUrl();
        imgBackground = user.getImgBackground();
        numFollowers = user.getNumFollowers();
        numFollowing = user.getNumFollowing();
    }
}
