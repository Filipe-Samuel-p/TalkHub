package filipeProject.example.authenticationJwt.repositories;

import filipeProject.example.authenticationJwt.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {
}
