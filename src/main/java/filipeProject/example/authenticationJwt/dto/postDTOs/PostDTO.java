package filipeProject.example.authenticationJwt.dto.postDTOs;


import filipeProject.example.authenticationJwt.dto.userDTOs.UserDTO;
import filipeProject.example.authenticationJwt.entities.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;


@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PostDTO {
    private Long id;
    private String text;
    private Instant creationDate;
    private UserDTO user;


    public PostDTO(Post entity) {
        id = entity.getId();
        text = entity.getText();
        creationDate = entity.getCreationDate();
        user = new UserDTO(entity.getUser());
    }

}

