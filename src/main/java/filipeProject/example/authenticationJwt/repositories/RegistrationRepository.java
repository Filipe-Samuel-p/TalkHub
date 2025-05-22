package filipeProject.example.authenticationJwt.repositories;

import filipeProject.example.authenticationJwt.entities.Registration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistrationRepository extends JpaRepository<Registration,Long> {
}
