package filipeProject.example.authenticationJwt.entities;

import filipeProject.example.authenticationJwt.enums.DifficultyTalkLevel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Duration;
import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "tb_talk")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Talk {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;
    private Instant startTime;


    private Duration duration;
    private int totalCapacity;
    private int numberAvailable;
    private String local;

    @Enumerated(EnumType.STRING)
    private DifficultyTalkLevel difficultyLevel;

    @OneToMany(mappedBy = "talk")
    private List<Registrations> registrations;

    @ManyToOne
    @JoinColumn(name = "speaker_id")
    private Speaker speaker;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    @ManyToMany
    @JoinTable(name = "tb_talk_category",
    joinColumns = @JoinColumn(name = "talk_id"),
    inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categories = new HashSet<>();

}
