package filipeProject.example.authenticationJwt.repositories;

import filipeProject.example.authenticationJwt.entities.SpeakerRequest;
import filipeProject.example.authenticationJwt.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface SpeakerRequestRepository extends JpaRepository<SpeakerRequest,UUID> {

    Optional<SpeakerRequest> findByUser(User user);

    List<SpeakerRequest> user(User user);
}
