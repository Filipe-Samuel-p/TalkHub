package filipeProject.example.authenticationJwt.repositories;

import filipeProject.example.authenticationJwt.entities.Speaker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpeakerRepository extends JpaRepository<Speaker,Long> {
}
