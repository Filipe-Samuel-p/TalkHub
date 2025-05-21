package filipeProject.example.authenticationJwt.repositories;

import filipeProject.example.authenticationJwt.entities.TalkRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TalkRequestRepository extends JpaRepository<TalkRequest, Long>{
}
