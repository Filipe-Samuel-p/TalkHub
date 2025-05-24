package filipeProject.example.authenticationJwt.repositories;

import filipeProject.example.authenticationJwt.entities.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("""
        SELECT p
        FROM Post p
        WHERE p.user IN (
            SELECT u.following
            FROM User u
            WHERE u.id = :userId
        )
        ORDER BY p.creationDate DESC
        """)
    Page<Post> findPostsFromFollowedUsers(@Param("userId") UUID userId, Pageable pageable);
}

