package filipeProject.example.authenticationJwt.repositories;

import filipeProject.example.authenticationJwt.entities.Speaker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SpeakerRepository extends JpaRepository<Speaker, UUID> {
}
