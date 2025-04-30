package filipeProject.example.authenticationJwt.repositories;

import filipeProject.example.authenticationJwt.entities.Talk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TalkRepository extends JpaRepository<Talk, Long> {
}
