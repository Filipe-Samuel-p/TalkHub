package filipeProject.example.authenticationJwt.service;

import filipeProject.example.authenticationJwt.dto.postDTOs.PostDTO;
import filipeProject.example.authenticationJwt.dto.postDTOs.PostWithoutUserDTO;
import filipeProject.example.authenticationJwt.dto.userDTOs.UserDTO;
import filipeProject.example.authenticationJwt.entities.Post;
import filipeProject.example.authenticationJwt.exceptions.ResourceNotFoundException;
import filipeProject.example.authenticationJwt.repositories.PostRepository;
import filipeProject.example.authenticationJwt.repositories.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.util.UUID;

@Service
public class FeedService {

    private final PostRepository repository;
    private final UserRepository userRepository;

    public FeedService(PostRepository repository, UserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }

    public PostWithoutUserDTO newPost(PostWithoutUserDTO dto, JwtAuthenticationToken token){

        var newPost = new Post();
        newPost.setText(dto.getText());
        newPost.setCreationDate(Instant.now());

        var loggedInUser = userRepository.findById(UUID.fromString(token.getName()))
                .orElseThrow(()-> new ResourceNotFoundException("Usuário não encontrado"));

        newPost.setUser(loggedInUser);
        repository.save(newPost);

        return new PostWithoutUserDTO(newPost);
    }

    public Page<PostDTO> getPostsFromFollowed(UUID userId, Pageable pageable) {
        var posts = repository.findPostsFromFollowedUsers(userId, pageable);
        return posts.map(PostDTO::new);
    }
}
