package filipeProject.example.authenticationJwt.repositories;

import filipeProject.example.authenticationJwt.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
}
