package filipeProject.example.authenticationJwt.repositories;

import filipeProject.example.authenticationJwt.entities.Role;
import filipeProject.example.authenticationJwt.enums.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByAuthority(RoleName authority);

}
