package filipeProject.example.authenticationJwt.repositories;

import filipeProject.example.authenticationJwt.entities.Event;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event,Long> {
    @Query("SELECT e FROM Event e WHERE e.startDate >= :now ORDER BY e.startDate ASC")
    List<Event> findUpcomingEvents(Instant now, Pageable pageable);
}
