package filipeProject.example.authenticationJwt.dto.speakerDTOs;


import filipeProject.example.authenticationJwt.entities.SpeakerRequest;
import filipeProject.example.authenticationJwt.entities.User;
import filipeProject.example.authenticationJwt.enums.RequestStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class SpeakerRequestDTO {

    private UUID id;
    private UserDTO user;
    private Instant requestDate;
    private RequestStatus status;


    public SpeakerRequestDTO(SpeakerRequest request){
       id = request.getId();
       user = new UserDTO(request.getUser());
       requestDate = request.getRequestDate();
       status = request.getStatus();

    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    private static class UserDTO{
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

}
