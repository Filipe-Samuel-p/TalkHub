package filipeProject.example.authenticationJwt.repositories;

import filipeProject.example.authenticationJwt.entities.Talk;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TalkRepository extends JpaRepository<Talk, Long> {

    List<Talk> findTop20ByOrderByIdDesc();

    @Query("SELECT t FROM Talk t WHERE t.category.id = :categoryId")
    List<Talk> findByCategoryId(Long categoryId, Pageable pageable);

    @Query("SELECT t FROM Talk t WHERE t.event.id = :eventId")
    List<Talk> findByEventId(Long eventId, Pageable pageable);

    @Query("SELECT t FROM Talk t WHERE t.category.id = :categoryId AND t.event.id = :eventId")
    List<Talk> findByCategoryIdAndEventId(Long categoryId, Long eventId, Pageable pageable);
}

