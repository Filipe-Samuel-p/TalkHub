package filipeProject.example.authenticationJwt.repositories;

import filipeProject.example.authenticationJwt.entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Event,Long> {
}
