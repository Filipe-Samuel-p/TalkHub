package filipeProject.example.authenticationJwt.dto.postDTOs;

import filipeProject.example.authenticationJwt.entities.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PostWithoutUserDTO {

    private Long id;
    private String text;
    private Instant creationDate;

    public PostWithoutUserDTO(Post entity){
        id = entity.getId();
        text = entity.getText();
        creationDate = entity.getCreationDate();
    }
}
